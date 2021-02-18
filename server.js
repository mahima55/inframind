const express = require("express");
const app = express();
const port = 1234;
const { google } = require("googleapis");
const cors = require("cors");
const urlParse = require("url-parse");
const queryParse = require("body-parser");
const axios = require("axios");
const fs = require('fs');

app.get('/getUsers', function(req,res){
  fs.readFile(__dirname + "/" + "datasource.json",'utf-8',function(err,data){
    console.log(data);
    res.end(data);
  });
})

app.use(cors());
app.use(bodyParser.urlencoded({ extended: true}));
app.use(bodyParser.json());


app.get("/gerURlTing" , (req , res) => {
  const oauth2Client = new google.auth.OAuth2(
    "672342265446-hb4g3noa5rtosg37cujkjn20vugvnra.apps.googleusercontent.com",
    "AIzaSyC-C9gSzaQ674tpYKj9ZPn0wqVefxyQqMI",
    //link to redirect
    "http://localhost:1234/steps"
  );
  const scopes = ["https://www.googleapis.com/auth/fitness.activity.read profile email openid"]
  const url = oauth2Client.generateAuthUrl({
    access_type: "offline",
    scope: scopes,
    state: JSON.stringify({
      callbackUrl : req.body.callbackUrl,
      userID: req.body.userid
    })
  })
  request(url,(err,response,body) => {
    console.log("error: ", err);
    console.log("statusCode: ",response && response.statusCode);
    res.send({url});
  })
});

app.get("/steps",(req,res) => {
  const queryURL =new urlParse(req.url);
  const code = queryParse.parse(queryURL.query).code;
  const oauth2Client = new google.auth.OAuth2(
    "672342265446-hb4g3noa5rtosg37cujkjn20vugvnra.apps.googleusercontent.com",
    "AIzaSyC-C9gSzaQ674tpYKj9ZPn0wqVefxyQqMI",
    //link to redirect
    "http://localhost:1234/steps"
  );
 const tokens = await oauth2Client.getToken(code);
 console.log(tokens);
 res.send("HELLO");

 let stepArray = [];
 try{
   const result = await axios({
     method: "POST",
     headers: {
       authorization: "Bearer"+ tokens.tokens.access_token
     },
     "Content-Type": "application/json",
     url: 'https://www.googleapis.com/fitness/v1/users/hp/dataset:aggregate',
     data:{
       aggregateBy :[
         {
           dataTypeName: "com.google.step_count.delta",
           dataSourceId: "derived:com.google.step_count.delta:com.google.android.gms:estimated_steps"
         }
       ],
       bucketByTime: {durationMillis: 86400000},
       startTimeMillis: 1585785599000,
       endTimeMillis: 1585958399000,
     },
   });
   console.log(result);
   stepArray=result.data.bucket
 } catch(e){
   console.log(e)
 }
 try{
   for(const dataSet of stepArray){
     console.log(dataSet);
     for(const points of dataset.dataSet){
       console.log(points);
       for(const steps of points.point){
         console.log(steps.value);
       }
     }
   }
 }catch(e){
   console.log(e)
 }
});

app.listen(port, () => console.log('GOOGLE FIT IS LISTENING ${port}'));
