<?PHP 

include_once("connection.php"); 
if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){ 
    if( isset($_POST['txtAddress']) ) { 
        $address = $_POST['txtAddress'];

        $restaurantInfoQuery = "SELECT RestaurantID, Name, Phone FROM Restaurants WHERE address = '$address'";

        $dishesQuery = "SELECT restaurantmenus.MenuID AS MenuID, categories.CategoryID AS CategoryID, categories.Name as CategoryName, dishes.DishID AS DishID, dishes.Name as DishName, dishes.Price as DishPrice FROM restaurants INNER JOIN restaurantmenus ON restaurantmenus.RestaurantID = restaurants.RestaurantID INNER JOIN menucategories ON menucategories.MenuID = restaurantmenus.MenuID INNER JOIN categories ON categories.CategoryID = menucategories.CategoryID INNER JOIN categorydishes ON categorydishes.CategoryID = categories.CategoryID INNER JOIN dishes ON dishes.DishID = categorydishes.DishID WHERE address = '$address' ORDER BY MenuID, CategoryID, CategoryName, DishID, DishName, DishPrice"; 

        $result = mysqli_query($connection, $restaurantInfoQuery);
        $row = mysqli_fetch_row($result);
        $restaurantInfo = array(
            "RestaurantID" => $row[0],
            "Name" => $row[1],
            "Phone" => $row[2],
            "Address" => $address
            );

        $result = mysqli_query($connection, $dishesQuery);
        $dishes = array();

        while ($row = mysqli_fetch_row($result)) {
            $oneDish = array(
                "MenuID" => $row[0],
                "CategoryID" => $row[1],
                "CategoryName" => $row[2],
                "DishID" => $row[3],
                "DishName" => $row[4],
                "DishPrice" => $row[5]
                );
            array_push($dishes, $oneDish);
        }

        $jsonData = new stdClass();
        $jsonData->restaurantInfo = $restaurantInfo;
        $jsonData->dishes = $dishes;

        echo json_encode($jsonData);


/*
        $rows = mysqli_fetch_row($dishes);
        $numRowDishes = $dishes->num_rows;
        while ($numRowDishes > 0){
            echo $rows[$numRowDishes];
            
            $numRowDishes--;
        } */
    } 
}

?>