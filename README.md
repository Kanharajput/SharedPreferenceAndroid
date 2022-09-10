# SharedPreferenceAndroid
It saves the data in a file with key-value pair and remain them till we delete that file.

Android SharedPreferences
The Simplest mechanism to store data in
android. we don’t have to worry about creating files, and APIs.
To access, we need to only have the XML file which is created and automatically managed.

What is Android SharedPreferences
–> It Stores Key/value Pairs, and then we use the appropriate key to get that value later. SharedPreferences is used by an application to save data in name-value pairs, like a Bundle Object.
–> Data is Stored Insdie the XML file in the directory /data/data/package_name/shared_prefs/preferences_name.xml
–> As Said we don’t have to worry about creating this XML files or accessing it or setting the permission and all.
This is why this is the simplest data storage mechanics of all.

So what kind of data be stored?
SharedPrefrences only allows you to save primitive data types like booleans, floats, longs, int, arrays and strings.If you want to map simple values (like int, boolean, String) then use Preferences.
