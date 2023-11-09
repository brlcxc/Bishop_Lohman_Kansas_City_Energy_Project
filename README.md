# Bishop_Lohman_Kansas_City_Energy_Project

The purpose of the application is to act as an interface for employees at a hypothetical energy company to access and modify customer data. Through the application, employees can create new customer profiles, access and modify existing customer demographic data, process customer payments, view energy usage, and send a customer invoice through email. The thought process behind the application is for the employee to be using it while on a call with a customer or when viewing customer documents.

## Usage
### Login Page

On the login page there are fields to enter a username and password with a button to log in. The intention behind these fields is to act as a spot for an employee to enter their employee credentials. 

`Note`: For the purposes of this application, the username and password will be the same as the credentials needed to access the connected database

<img src="https://github.com/brlcxc/Bishop_Lohman_Kansas_City_Energy_Project/assets/90850429/fe390360-af7d-4b29-b7ac-ee8b40d0b3e2" width="600">

### Employee Option Page

The next page has an option to access an existing customer as well as the option to create a new customer profile. 

`Note`: The employee username along with an option to sign out are now displayed in the top right corner. These will remain in place until the employee signs out. Pressing “sign out” will bring the employee back to the login page 

<img src="https://github.com/brlcxc/Bishop_Lohman_Kansas_City_Energy_Project/assets/90850429/6aee9dce-5970-489b-9473-76b86903e98f" width="600">

### Creating a New Customer Profile

If the employee selects the option to create a new user profile, they will be directed to a page where the customer demographics, as well as some energy information, can be input. Once the employee presses “confirm,” a customer ID is assigned to that customer

`Note`: For the purposes of this application a random energy usage is selected.

<img src="https://github.com/brlcxc/Bishop_Lohman_Kansas_City_Energy_Project/assets/90850429/03a8db61-c57c-47ad-92d8-755fce6b6a98" width="600">
<img src="https://github.com/brlcxc/Bishop_Lohman_Kansas_City_Energy_Project/assets/90850429/fd4b083b-d796-40aa-92e5-be1655d21006" width="600">

### Accessing an Existing Customer

If the employee selects the option to access an existing customer, the employee will be prompted to enter a valid customer ID. 

`Note`: The customer ID is a unique number that is associated with each individual customer. The intention is for the employee to get the customer ID either directly from the customer while on a call, or obtain the ID while viewing customer documents.

<img src="https://github.com/brlcxc/Bishop_Lohman_Kansas_City_Energy_Project/assets/90850429/11259c1a-b7ec-4b10-a7bc-9aad2ddd8497" width="600">

### Customer Option Page 

Once a valid customer ID is entered, the employee will have the options to either view customer information, process a payment, delete the customer profile, view the energy usage, send an invoice, or exit the customer page. If the user presses the back arrow, they will be brought back  to the prior screen that has an option to access an existing customer as well as the option to create a new customer profile

`Note`: The current customer ID is now displayed in the top left corner

<img src="https://github.com/brlcxc/Bishop_Lohman_Kansas_City_Energy_Project/assets/90850429/f5f24cca-25da-4f0c-95fe-8094bd1c3b06" width="600">

### Viewing Customer Information

If the employee presses “view customer information,” they will be brought to a screen with the customer’s demographic data as well as the energy tariff and meter type.
The user can press “edit customer information” to modify this information. Another screen will appear with fields for the data which can be edited.

`Note`: For the purposes of this application, the total cost will reset when the customer information is changed. This is to account for the updated energy tariff that gets applied

<img src="https://github.com/brlcxc/Bishop_Lohman_Kansas_City_Energy_Project/assets/90850429/62a4bbe9-ac43-4c2b-abdc-a2edfb0b0329" width="600">
<img src="https://github.com/brlcxc/Bishop_Lohman_Kansas_City_Energy_Project/assets/90850429/48f7ceda-ce67-4dcd-ae25-162ea674dbad" width="600">

### Processing a Payment

If the employee selects “process payment,” they will be brought to a screen where either a partial payment can be entered or a full payment can be made

<img src="https://github.com/brlcxc/Bishop_Lohman_Kansas_City_Energy_Project/assets/90850429/6851f7b9-cf02-4c01-bbd2-93fde56244cc" width="600">

### Viewing Energy Usage

If the employee selects “view energy usage,” a breakdown on the energy usage is given

<img src="https://github.com/brlcxc/Bishop_Lohman_Kansas_City_Energy_Project/assets/90850429/fa88084c-dfe0-4ed4-abe7-45f2d20e47c8" width="600">

### Sending An Invoice

If the employee selects “send invoice,” they will be brought to a page with a default email to the customer which includes the monthly bill prepared. The employee has the option to edit this email. Pressing “send” will create a pdf of the invoice and send an email.

<img src="https://github.com/brlcxc/Bishop_Lohman_Kansas_City_Energy_Project/assets/90850429/3d822631-0717-4e1b-acde-c4fa79a9d854" width="600">
<img src="https://github.com/brlcxc/Bishop_Lohman_Kansas_City_Energy_Project/assets/90850429/b53f01e9-cd55-43a2-9a4b-55f089ed637e" width="600">

## Setup

### Connecting The Database

For the application to be run, a database needs to be connected. For this project, MySQL was used in combination with the Database Navigator plugin on Intellij. To initialize the database, the file named “CustomerData.sql” within the Logic package was selected and run within MySQL. From there, that database was connected to the project with the Database Navigator plugin.

### Sending Emails
The application sends emails via the MailUtil class. This code is set up to send emails from my personal email. For security purposes, the password to my email has been removed from the code. To use this feature with another Gmail account, the account will have to be set up properly and the information from it needs to be brought into the code 

### Development 
