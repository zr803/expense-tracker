# Budget/Expense Tracker

## Program Intentions and Usage

For my project I want to create a budget tracking system for **students and workers**. 
Since coming to university and having to keep track of my own expenses, I have learned how quickly things can add up
and often find myself wondering how much I have spent so far. For a while I have been wanting to start 
writing it down, but I thought that this would be a good tool to create so that students like me, as well as workers
can have a place to keep track.

The application will allow users to create a tracker for their expenses, where they can input when they spent their 
money, on what they spent it, and how much it costed. They will also be able to get a total of their expenses and remove
an entry as well, which could be useful if they have paid that expense off. After the entries have been added the user 
will be able to produce a graphical table displaying all of this information in an organized manner. 


## User Stories
- As a user, I want to be able to add multiple expenses to my tracker.
- As a user, I want to be able to be able to view a list of expenses for which I have recorded.
- As a user, I want to be able to get how much my net expenses are from my tracker.
- As a user, I want to be able to remove an entry from the tracker once I have paid the expense off. 
- As a user, I want to be able to save my expense tracker to file.
- As a user, I want to be able to load my expense tracker from file.

## Instructions for Grader
- You can generate the first required event related to adding Xs to a Y by filling out the required information in the
  'create a new entry' panel, then click the 'Add Entry' button.
- You can generate the second required event related to adding Xs to a Y by entering the label of the expense entry you
  would like to remove in the panel titled 'Remove and entry', and then click the 'Remove Entry' button. 
- You can locate my visual component, a customized logo graphic, in the bottom right corner of the window when you run
  the application.
- You can save the state of my application by clicking File -> Save on the menu at the top of the window.
- You can reload the state of my application by clicking File -> Open on the menu at the top of the window.

## Phase 4: Task 2
Here is what was printed when I ran my GUI and added a couple of entries and removed a couple before closing the window:

A new entry has been added to your expenses. \
Wed Nov 23 10:31:38 PST 2022 \
A new entry has been added to your expenses. \
Wed Nov 23 10:31:49 PST 2022 \
An entry has been removed from your expenses. \
Wed Nov 23 10:32:30 PST 2022 \
A new entry has been added to your expenses. \
Wed Nov 23 10:33:03 PST 2022 \
An entry has been removed from your expenses.

## Phase 4: Task 3
While it may not be noticeable in my UML diagram, the cohesion could be improved since there are multiple
responsibilities in the ExpenseTrackerGUI class. For example the class is responsible for adding and removing 
entries to the display as well as constructs the overall display, so there are a lot of lines of code. So some changes I
could make may include...
- Creating a class for persistence related tasks in the GUI, that is a class responsible for saving and loading the 
  state of the display.
- Creating separate classes for removing and adding entries to the display. This may include, for example, creating a
  class responsible for the display panel where you create an entry, and another responsible for adding the entry to the
  GUI.

Furthermore, in my GUI where I create the display, there are some lines of code that are very similar to each other, as 
they use the same layouts or create rigid areas between components that are the same size. To improve this I could 
create methods to cut down the duplicate code and improve the single point of control in case I want to change anything
in the future, like making the gaps bigger.