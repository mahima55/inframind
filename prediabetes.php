<html>
<head>
  <title>Predict Prediabetes</title>
  <style>

  h2{

    height: 40px;
    width: 63%;
    margin-top: 40px;
    padding-top: 10px;
    padding-left: 480px;
  }
.table2{

  border-collapse: collapse;
  font-family: arial,sans-serif;
  margin-top: 30px;
  margin-left: 300px;
}
.table2 tr,th,td{
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;

}
.table2 td{
  width: 400px;
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

  <h2>List of Employees likely to have Prediabetes</h2>
<div class="table2">
  <table>

    <tr>
      <th>UserName</th>
      <th>Blood Glucose</th>

    </tr>

  <?php

  include "dbConn.php"; // Using database connection file here

  $records = mysqli_query($db,"select * from employee WHERE BloodGlucose > 144 && BloodGlucose < 199"); // fetch data from database

  while($data = mysqli_fetch_array($records))
  {
  ?>
    <tr>
      <td><?php echo $data['UserName']; ?></td>
      <td><?php echo $data['BloodGlucose']; ?></td>

    </tr>
  <?php
  }
  ?>
  </table>

  <?php mysqli_close($db); // Close connection ?>
</div>
</body>
</html>
