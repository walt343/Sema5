# Sema4

This is the repository for USNA CS/IT Department 2016 Capstone Group 5

The purpose of this project is to do signals processing for three signals on an Android device.

#Signals
1. ACARS
2. End-of-Train Device
3. Handheld Radio

#Where we are
Right now, we have a prototype GUI for the phone working with an ability to connect to an attached RTL_SDR and collect a short sample of radio energy and save the raw bytes (proof of byte stream).  Also, we have classes for saved messages for two of the three signals.  These then can be reviewed and displayed.  They are saved as simple text.

#Usage
Upon opening the application, tap the button for your desired signal.  For the handheld radio, first enter a frequency (in MHz) then tap "Connect" to connect to the SDR.  To start a quick stream, tap "Save."  To review saved packets, tap "Read."  For the other signals, entering the desired frequency is unnecessary.

#Progress
As of right now, we have bytestreams for each signal and a prototype interface to work with.  The next steps involve demodulating, decoding, and parsing the raw bytes we receive from the SDR.  In essence, we have the beginning and end parts done and are working to the middle.
