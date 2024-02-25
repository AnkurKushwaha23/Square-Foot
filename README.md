#SquareFoot - Construction and Carpentry Calculator App

#Overview

SquareFoot is an Android application specifically designed in the construction and carpentry fields. This app simplifies the calculation process for project costs, allowing users to input length, width, and price per unit. The app features a MainActivity for calculations, a SavedDataActivity for managing and viewing saved projects, and a DetailActivity for exploring detailed project information.

#Features

Input Form:-
Users can input the length, width, and price per unit in feet.
The app calculates the total cost based on the provided inputs.

Saved Data Activity:-
The MainActivity features a "View Saved Data" button.
Clicking this button opens the SavedDataActivity containing a RecyclerView of saved projects.

Local Database:-
Utilizes SQLite to store project details locally.
Saves data, including the date, length, width, price per unit, and total cost.

RecyclerView in SavedDataActivity:-
Lists saved projects with a summary of each project (e.g., date, total cost).

Detail Activity:-
Users can click on any item in the RecyclerView in SavedDataActivity to view detailed project information.
The DetailActivity includes the date, length, width, price per unit, and total cost.
Provides the option to delete specific entries.

#Usage

Launch the SquareFoot App:-
Open the app; it displays the MainActivity for calculations.
Calculate Project Cost:-
Input the length, width, and price per unit in MainActivity.
Click on the calculate button to view the total cost.
View Saved Data:-
Click on the "View Saved Data" button in MainActivity.
It opens the SavedDataActivity containing a list of saved projects using RecyclerView.
Detailed View:-
In SavedDataActivity, click on any item to view detailed project information.
Opened DetailActivity allows users to delete the specific entry if needed.

#Dependencies

SQLite Database for local storage.
RecyclerView for displaying the list of saved projects.

#Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or create a pull request.

#ScreenShorts

![Screenshot_20240222-074456](https://github.com/AnkurKushwaha23/Square-Foot/assets/157258878/28ac8a51-573c-4c4d-8a4c-1f641b3e0121)

![Screenshot_20240222-074507](https://github.com/AnkurKushwaha23/Square-Foot/assets/157258878/2801e2ad-7c6d-4045-b1d5-e2b0a000db01)

![Screenshot_20240222-074529](https://github.com/AnkurKushwaha23/Square-Foot/assets/157258878/2012a4df-888c-4289-bcb6-f25b16da0368)

![Screenshot_20240222-074549](https://github.com/AnkurKushwaha23/Square-Foot/assets/157258878/fc88dbe4-cc17-4e8b-be30-9f6faa686684)

![Screenshot_20240222-074601](https://github.com/AnkurKushwaha23/Square-Foot/assets/157258878/b09f251a-dcf7-443b-9e32-1f4c236c10fd)

![Screenshot_20240222-074706](https://github.com/AnkurKushwaha23/Square-Foot/assets/157258878/5d31025e-5578-4d2c-ac7f-b433d7a226f3)

