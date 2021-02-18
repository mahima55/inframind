<html>
<head>
  <title>Predict Prediabetes</title>
  <style>

  h2{

    height: 40px;
    width: 71%;
  
    padding-top: 10px;
    padding-left: 370px;
    margin-top: 40px;
  }
.table2{

  border-collapse: collapse;
  font-family: arial,sans-serif;
  margin-top: 30px;
  margin-left: 120px;
}
.table2 tr,th,td{
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;

}
.table2 td{
  width: 350px;
  height: 60px;
}
.table2 th{
  height: 50px;
  background-color: darkblue;
  color: white;
}


    </style>
</head>
<body>

  <h2>List of Employees likely to have Coronary Heart Disease</h2>
<div class="table2">
  <table>

    <tr>
      <th>UserName</th>
      <th>Blood Pressure</th>
      <th>Heart Rate</th>
    </tr>

  <?php

  include "dbConn.php"; // Using database connection file here

  $records = mysqli_query($db,"select * from employee WHERE HeartRate > 70 AND BloodPressure > 120/80"); // fetch data from database

  while($data = mysqli_fetch_array($records))
  {
  ?>
    <tr>
      <td><?php echo $data['UserName']; ?></td>
      <td><?php echo $data['BloodPressure']; ?></td>
      <td><?php echo $data['HeartRate']; ?></td>
    </tr>
  <?php
  }
  ?>
  </table>

  <?php mysqli_close($db); // Close connection ?>
</div>
</body>
</html>
