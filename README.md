# Sensor_Data_Logger

App Development for Smart Devices

CS 441/541

Assignment #2: SENSOR DATA LOGGER

Published: Thursday, Feb 20, 2020

Deadline: 11:59 PM, Tuesday, March 3, 2020

All projects are required to be submitted with up to a one-page report that specifies what was
implemented, and to what degree it was implemented. You need to also justify your design
decisions. Graduate students may go beyond one page, with additional information regarding
implementation of the graduate student requirements.

We’re going to build a sensor data logger. The goal of this app is to be able to log data from
various sensors into a text/csv file. The requirements for the layout for the Home Screen/ Main

Activity (Let’s call this Activity A) are:
1. There should be a general title: Sensor Data Logger (5 points) -- DONE

2. There must be a list of following sensors (5 points) -- DONE

• Accelerometer -- DONE

• Gyroscope -- DONE

• Magnetometer -- DONE

• Ambient Light Sensor -- DONE

• A sensor of your choice -- DONE  (Ambient Tempurature)

3. (30 points total) Upon clicking any sensor from the list, a new activity (Let’s call this
Activity B) is triggered. Activity B must:

• Have a title that corresponds to the sensor selected from the main list (5 points) -- DONE

• Have a button ‘Back’ in the bottom left corner (5 points) -- DONE

• Hitting ‘Back’ button must take the user back to the main list of sensors (5
points) -- DONE

• Display sensor data on the screen, one row for each value (5 points) -- DONE

• Each row must have a label for the value and the value captured from the sensor
in front of it (5 points) -- DONE

• Have a ‘Start’ and a ‘Stop’ Button below the sensor data display (5 points) -- DONE

### Need to add runtime user permission request ###

4. (15 points total) Hitting the ‘Start’ button must

• Create a .txt or .csv file on the device’s storage (5 points) -- DONE

• Write data to this file in the following format. There must be one row for each
sample. (5 points)

Timestamp, Sensor Name, Sensor Value 1, Sensor Value 2, … Sensor value n -- DONE

• Display a string on Activity B: “Data Recording” (5 points) -- DONE

5. (10 points total) Hitting the ‘Stop’ button must

• Stop writing to the file (5 points) -- DONE

• Erase the ‘Data Recording’ message (5 points) -- DONE

6. (25 points total) If the user hits ‘Back’ in the middle of recording:

• A dialog box must show up asking the user if they want to stop recording. (10
points)

• If the user selects ‘Yes’, the recording is stopped (5 points) and the user is back
to Activity A (5 points)

• If the user selects ‘No’, the user stays on Activity B and recording is not
interrupted (5 points)

Graduate students’ additional requirements [20 points total]

Add a third button to Activity B “Record Event”. When the user hits this button, the timestamp
is logged in the same .txt or .csv file in the following format:

Timestamp, Event

The button must only record the event if the sensor is currently recording data.
