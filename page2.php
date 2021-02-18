<html>
<head>
  <title>View Data by Username</title>
  <style>
  table{
  width: 100%;
  border-collapse: collapse;
  font-family: arial,sans-serif;
  margin-top: 50px;
}
tr,th,td{
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}
h2{
  margin-left: 500px;
  margin-top: 70px;
  font-size: 40px;
}
.searchbar{
  width: 100px;
  height: 30px;
  font-size: 15px;
  color: white;
  background-color: darkblue;
  border: 5px solid darkblue;
  margin-left: 30px;
}
.sbar{
  width: 200px;
  height: 30px;
  font-size: 15px;
  border: 0.5px solid grey;
}
  </style>
</head>
<body>
  <h2>Search For Employees</h2>
  <center>
    <div class="container">
      <form action="" method="POST">
        <input type="text" name="id" placeholder="Enter Username" class="sbar"/>
        <input type="submit" name="search" value="SEARCH" class="searchbar">
      </form>
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
        if(isset($_POST['search'])){
          $id = $_POST['id'];
        $records = mysqli_query($db,"select * from employee where UserName = '$id' "); // fetch data from database

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
      }
        ?>
        </table>

        <?php mysqli_close($db); // Close connection ?>
    </div>
  </center>
</body>
</html>
