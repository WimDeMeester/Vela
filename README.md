Vela Telescope Control System
=============================

Vela is an Android application to control the fan, heater, temperature sensors,
digital setting circles, and motor drive of a Dobsonian telescope. 

You need :
  - an Android smartphone or tablet, with at least Android 4.4 (for the Bluetooth BLE board),
  - an Arduino Uno (€24.20),
  - a RedBearLab BLE Shield for the bluetooth connection (http://redbearlab.com/bleshield/, €24.08),
  - the Arduino software for Telescope Control,
  - digital encoders,
  - temperature sensors,
  - a fan to cool the primary mirror,
  - heaters,
  - motors for the azimuth and altitude axis.
  
The Android application will interface with DeepskyLog (http://www.deepskylog.org) to get a list with objects
and to import the Observing Lists. When connected to the internet, it will be possible to:
  - show the star charts from DeepskyLog directly on the smartphone,
  - show the observations from DeepskyLog for the object you are looking at,
  - directly add an observation to DeepskyLog.

Telescope Control is developed to work with my telescope and my settings. I hope to find some testers and developers
who want to test the software on other setups.

Some things to think about for future versions : 
  - Download location and get drive directions using google maps.
  - ...
