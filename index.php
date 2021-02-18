<!DOCTYPE html>
<html>
<head>
  <title>Complete Details</title>
  <style>
  table{
  width: 100%;
  border-collapse: collapse;
  font-family: arial,sans-serif;
  margin-top: 30px;
}
tr,th,td{
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}
th{
  height: 30px;
  background-color: darkblue;
  color: white;
}
td{
  height: 30px;
}
h2{
  color: black;
  margin-left: 410px;
  margin-top: 30px;
  font-family: serif;

}
.bt2{
    margin-top: 20px;
    width: 140px;
    height: 40px;
    background: darkblue;
    border: 1px solid darkblue;
    margin-left: 1100px;
}
.bt2 a{
  text-decoration: none;
    color: white;
    font-weight: bold;
    font-size: 15px;
}


.m1{
  margin-top: 10px;
  font-size: 20px;
  color: red;
  font-family: serif;
  font-weight: bold;
}
.navbar {
  overflow: hidden;
  background-color: darkblue;
  margin-top: 30px;
  height: 50px;

}

.navbar a {
  float: left;
  font-size: 20px;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-family: serif;
}
.dropdown {
  float: left;
  overflow: hidden;
}

.dropdown .dropbtn {
  font-size: 20px;
  border: none;
  outline: none;
  color: white;
  padding: 14px 16px;
  background-color: inherit;
  font-family: serif;
  margin: 0;
}
.navbar a:hover, .dropdown:hover .dropbtn {
  background-color: black;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}
.dropdown-content a {
  float: none;
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

.dropdown-content a:hover {
  background-color: #ddd;
}

.dropdown:hover .dropdown-content {
  display: block;
}

  </style>
</head>
<body>
<marquee class="m1">Keep in regular contact with loved ones, for example by telephone, e-mail,
   social media or video conference in order to maintain your mental health.</marquee>

   <div class="navbar">
  <a href="#home">Home</a>
  <a href="info.php">View Details</a>
  <div class="dropdown">
    <button class="dropbtn">Predict Disease
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="prediabetes.php">Prediabetes</a>
      <a href="diabetes.php">Diabetes</a>
      <a href="bron.php">Bronchiectasis</a>
      <a href="chd.php">Coronary Heart Disease</a>
      <a href="hypo.php">Hypoxemia</a>
      <a href="asthma.php">Asthma</a>
    </div>
  </div>
</div>


<h2>List of Employees with their Current Health Status</h2>

<table>
  <tr>
    <th>UserName</th>
    <th>Blood Glucose</th>
    <th>Blood Pressure</th>
    <th>Body Temperature</th>
    <th>Heart Rate</th>
    <th>Oxygen Level</th>
    <th>Respiration Level</th>
  </tr>

<?php

include "dbConn.php"; // Using database connection file here

$records = mysqli_query($db,"select * from employee"); // fetch data from database

while($data = mysqli_fetch_array($records))
{
?>
  <tr>
    <td><?php echo $data['UserName']; ?></td>
    <td><?php echo $data['BloodGlucose']; ?></td>
    <td><?php echo $data['BloodPressure']; ?></td>
    <td><?php echo $data['BodyTemperature']; ?></td>
    <td><?php echo $data['HeartRate']; ?></td>
    <td><?php echo $data['OxygenLevel']; ?></td>
    <td><?php echo $data['RespirationLevel']; ?></td>
  </tr>
<?php
}
?>
</table>

<?php mysqli_close($db); // Close connection ?>


<button class="bt2"><a href="page2.php">Next Page</a></button>

</body>
</html>
