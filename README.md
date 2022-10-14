# LBL Trucking company CRM

Use Case diagram:
![](https://github.com/IHJavaGrupo6/Homework3/blob/58e517df89d7cef205ad0e619385516473bb6cfe/CRM%20Use%20Case%20diagram.png)
Class diagram:
![](https://github.com/IHJavaGrupo6/Homework3/blob/58e517df89d7cef205ad0e619385516473bb6cfe/CRM%20Class%20diagram.png)
## --Instructions--

==LBL Trucking company CRM==  
Menu:
~~~
=========  
MAIN MENU  
Available commands: 
• New SalesRep  
• Show SalesReps  to show the list of existing leads  
Existing leads: 0  
• New lead  
• Show leads  to show the list of existing leads  
Existing leads: 0  
• Look up lead + id  to find a lead by its id number and display its info  
• Convert lead + id  to find a lead by its id number and convert it into a new opportunity  
• Show opportunities  to show the list of existing opportunities (both open and closed)  
Existing opportunities: 0  
• Look up opportunity + id  to find a lead by its id number and display its info  
• Close-Won + id  to close an oportunity that ended with a sale  
• Close-Lost + id  to close a lost oportunity  
• Report  to access report features
• Exit  

What do you want to do?  
=========
~~~~
### Option -New SalesRep-
Insert the comamand "New SalesRep" (case insensitive) to generate a new SalesRep, introducing by inputs the lead name.
~~~~
=========
What do you want to do? 
new salesrep
Creating a new SalesRep: 
Please enter the name of the new SalesRep: 
Quim
New SalesRep created: 
SalesRep: id = 1, name = Quim
=========  
~~~~
=========

### Option -Show SalesReps-
Introduce the command "Show SalesReps" (case insensitive) to display the list of leads(HashMap object).

========= 
~~~
What do you want to do? 
show SalesReps
 Existing SalesReps: 

•SalesRep: id = 1, name = Quim
~~~
=========


### Option -New lead-
Insert the comamand "New Lead" (case insensitive) to generate a new lead, introducing by inputs the lead information(Name, Phone Number,
email and Company Name).

=========  
~~~
What do you want to do? 
new lead
Creating a new lead: 
Please enter the name of the new lead: 
Piero
Please enter a phone number for the new lead: 
111222333
Please enter an email for the new lead: 
piero@ibiza.com
Please enter the name of the company for the new lead: 
Ibiza
Please enter the id of the associated SalesRep: 
1
New lead created: 
Lead: id = 1, name = Piero, phoneNumber = 111222333, email = piero@ibiza.com, companyName = Ibiza, SalesRep name = Quim 
=========
~~~~

### Option -Show leads-
Introduce the command "Show leads" (case insensitive) to display the list of leads(HashMap object).
~~~~
=========  
What do you want to do?  
show leads  
Existing leads:

•Lead: id = 0, name = Quim, phoneNumber = 666999555, email = quim@myenterprise.com, companyName = My Enterprise

•Lead: id = 1, name = Aña, phoneNumber = 666777888, email = aña@myenterpryse.com, companyName = My Enterpryse 2

=========
~~~~

### Option -Look up lead + id-
Insert the command "look up lead" + id (case insensitive) to show the lead that corresponds to the entered numeric id.
~~~~
=========  
What do you want to do?  
look up lead 1  
Lead: id = 1, name = Aña, phoneNumber = 666777888, email = aña@myenterpryse.com, companyName = My Enterpryse 2  
=========
~~~~

### Option -Convert lead + id-
Insert the command "Convert" + id (case insensitive) to turn the lead into a contact, opportunity and an account. 
The command activatesa chain the command in which it will be shown that the contact has been generated, it will ask you to enter the
following data (Product type, number of trucks) to create the opportunity and finally you will be asked if you want to create a new account or associate the opportunity and contact with an existing account. If you type "Y" a new account will be created with the data (Industry type, nº employees, city and country) that the user will be asked for. If you type "N" you will be asked for an id of an existing account and the opportunity and contact will be added to that account.
~~~~
=========  
What do you want to do?  
convert 2
Lead converted into a new contact: 
Contact: name = Jose Caro, phoneNumber = 111222333, email = jose@caro.es, companyName = Ironhack

Creating a new opportunity: 
Please enter product type: HYBRID, FLATBED or BOX
box
Please enter the number of trucks considered for purchase: 
250
Created a new opportunity: 
Opportunity: id = 2, product = BOX, trucks quantity = 250, status = OPEN, SalesRep name = Quim
 Decision maker Contact: name = Jose Caro, phoneNumber = 111222333, email = jose@caro.es, companyName = Ironhack

Would you like to create a new Account? (Y/N): 
Y
Creating a new account: 
Please enter industry type: PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL or OTHER
other
Please enter the number of employees in the company: 
28
PLease enter the city in which the company is based: 
Bcn
PLease enter the country in which the company is based: 
España
Created a new account: 
Account: id = 2, industry= OTHER, employeeCount= 28, city= Bcn, country= España
 Contact List 
[Contact: name = Jose Caro, phoneNumber = 111222333, email = jose@caro.es, companyName = Ironhack
]
 Opportunity List 
[Opportunity: id = 2, product = BOX, trucks quantity = 250, status = OPEN, SalesRep name = Quim
 Decision maker Contact: name = Jose Caro, phoneNumber = 111222333, email = jose@caro.es, companyName = Ironhack
]
Lead 2 removed fromm the database.
=========
~~~~

### Option -Show opportunities-
Insert the command "show opportunities" (case insensitive) to display a list of all opportunities.

~~~~
=========  
What do you want to do?  
show opportunities  
Existing opportunities:  

•Opportunity: id = 0, product = HYBRID, trucks quantity = 50, status = OPEN  
Decision maker Contact: id = 0, name = Aña, phoneNumber = 666777888, email = aña@myenterpryse.com, companyName = My Enterpryse 2  
=========
~~~~

### Option -Look up opportunity + id-
Introduce the command "look up opportunity id" to show an opportunity by its id number.

~~~~
=========  
What do you want to do?  
look up opportunity 0  
Opportunity: id = 0, product = HYBRID, trucks quantity = 50, status = OPEN  
Decision maker Contact: id = 0, name = Aña, phoneNumber = 666777888, email = aña@myenterpryse.com, companyName = My Enterpryse 2  
=========
~~~~

### Option -Close-Won + id-
Introduce de command "close-won" + id (case insensitive) to close an opportunity that ended with a sale.

~~~~
=========  
What do you want to do?  
close won 0  
Opportunity: id = 0, product = HYBRID, trucks quantity = 30, status = CLOSED_WON  
Decision maker Contact: id = 0, name = Quim, phoneNumber = 666222111, email = quim@myenterpryse.com, companyName = My Enterpryse  
=========
~~~~

### Option -Close-Lost + id-
Insert de command "close-lost" + id (case insensitive) to close a lost opportunity by its id number.

~~~~
=========  
What do you want to do?  
close lost 0  
Opportunity: id = 0, product = HYBRID, trucks quantity = 30, status = CLOSED_LOST  
Decision maker Contact: id = 0, name = Quim, phoneNumber = 666444888, email = quim@myenterpryse.com, companyName = My enterpryse  
=========
~~~~

### Option -Report-
Insert the command "report" (case insensitive) to enter the menu of report features.

~~~
=========
### -Report Features menu-
REPORT MENU
Available commands: 
• SalesRep reports 
• Product reports 
• Country reports 
• City reports 
• Industry reports 
• Employee Count reports 
• Quantity reports 
• Opportunity reports 
• Back to main menu 
What do you want to do? 
=========
~~~~

First, you will have to select a category from which you want to print the reports. And the inside each category you will be givent different options.
For example, if you type "SalesRep" (case insensitive) you will arrive to the following menu:

~~~
=========
SalesRep
• Report Lead by SalesRep 
• Report Opportunity by SalesRep 
• Report Close-Won by SalesRep 
• Report Close-Lost by SalesRep 
• Report Open by SalesRep 
• Back 
=========
~~~~

After choosing a specific Report Feature you will receive a table with the corresponding results.
For example, if you type "report opportunity by sales rep" you will see something like this:

~~~~
=========
report opportunity by sales rep
Quim 2
Jaume 3
Sergi 1
=========
~~~~

=========
### If you type "back" (case insensitive) you will go back to the Main Menu.
=========

### Option -Exit-
Insert the command "exit" (case insensitive) to turn off the CRM.

~~~~
=========
What do you want to do?  
exit  
Good bye!  
=========
~~~~

## Testing

### AccountRepositoryTests

Here we tested the Account queries for the report features.

### OpportunityRepositoryTests

In this test we checked if the Opportunity queries work correctly.

### SalesRepRepositoryQuimTest

In this one we tested if the SalesRep tests work properly.


## Credits

### Renegados Staff

Irina Tataru - https://github.com/mirnaia83  
Aña Popova  - https://github.com/anya-chocolat  
Danny Mejía  - https://github.com/xpan1c  
Joaquim Crous  -  https://github.com/QuimCrous  
Oscar Curto  - https://github.com/OscarCurto
