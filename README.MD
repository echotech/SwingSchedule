# README For Jed's Scheduling App

How to use Jed's Scheduling App 

## Table of Contents


- [Usage](#usage)
- [Languages](#language support)
- [Support](#support)
- [Contributing](#contributing)


## Usage

Steps to...

Log in:
- Log in using demo for the username and password.

Create a customer:
- Create a customer using the create customer fields.
- Click "Add Customer."

Create an appointment:
- Select customer from the dropdown menu in the appointments section.
- Fill out all the fields and click "Add appointment."

Create a reminder:
- Enter the date you want to get reminded on.
- Enter the snoozeIncrement.
- Select the snoozeIncrement type.

Edit a customer:
- Double-click the field you want to edit.
- Click ok or click a different field.

Edit an appointment:
- Double-click the field you want to edit.
- Click ok or click a different field.


## Reports

Number Types By Month:
- Lists number of appointments for all months.

Schedule:
- Lists the schedule for all appointments.


## Language support

English, Spanish, French
-To set the language, change your locale settings in your operating system.
-If your language is set to English, Spanish, or French the error messages will
be displayed in those languages. If you have another language set, it will
default to French.

To change your locale:
1. View the System Locale settings for Windows
2. Click Start, then Control Panel
3. Click Clock, Language and Region
Windows 10, Windows 8: Click Region 
Windows 7: Click Region and Language
Windows XP: Click Regional and Language Options 
The Region and Language options dialog appears.
4. Click the Administrative tab
On Windows XP, click the Advanced tab 
If there is no Advanced tab, then you are not logged in with administrative privileges.
5. Under the Language for non-Unicode programs section, click Change system locale and select the desired language.
6. Click OK
7. Restart the computer to apply the change.

Alternately, you can comment/uncomment the following lines of code in Main.java:
- public static Locale locale= Locale.getDefault();    
- //public static Locale locale= new Locale("en","US");
- //public static Locale locale= new Locale("es","ES");
- //public static Locale locale = new Locale("fr","FR");


## Support

Please contact Jed at jreisne@wgu.edu for assistance.

## Contributing

No contributions please.
