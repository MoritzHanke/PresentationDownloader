# Warning
- This is not tested by now, but will be as the semester goes on...
- It supports only a small number of features but feel free to use and modify it for your needs.  
- Even though it should work on all platform the same, it will be only testet on Windows, so the syntax for paths or smth may change....  
- Personal use, don't expect any normalised syntax

# Requirements
Written in Java-11 but should run in Java-1.7+

# Installation
- Download the compiled file [DownloadPresentations.jar](DownloadPresentations.jar) 
and store it in your working directory.  
- Run the jar or manually create a "downloadConfig.cfg" file in the same folder
- [Optional] I recommend creating a shortcut to the jar and placing it on the desktop or in an easily accessible location

# Config-Configuration
The Config file is structured as follows:
```
".\localpath\name1.pdf" <modifier1.1> ... <modifier1.n> URL1
...
".\localpath_n\name_n.pdf" <modifier_n.1> ... <modifier_n.n> URL_N
```
where each line contains
- the local path and file ````.localpath\name1.pdf```` in quotation marks.  
Note that this has to be a valid (in case of syntax, not necessarily existing) path and that the datatype (in this case ```.pdf```) has to mach with source from the URL.  
- optional modifiers ```<modifier1.1> ... <modifier1.n>``` for changing URLs and file-names seperated by spaces (for example the week you are in).
- the URL ```URL``` to the document that should be downloaded. DO NOT use quotation marks. Spaces aren't valid in URLs so there's no need for them.  


# URL/Path-Snippets
These are short String that can be used in  ```URL``` or ```.localpath\name1.pdf``` that allow you to dynamically modify them as time goes on.

| Snippet     | Short description               | Description                                                                                        | Additional info             |
|-------------|---------------------------------|----------------------------------------------------------------------------------------------------|-----------------------------|
| #~ws~       | weeks passed                    | weeks passed since startDate set by [StartDate-Modifier](#modifiers). At least one Digit           | this is rounded down        |
| #~ws+~      | weeks passed + 1                | weeks passed since startDate set by [StartDate-Modifier](#modifiers) plus one. At least one Digit  | this is rounded down        |
| #~ws-~      | weeks passed - 1                | weeks passed since startDate set by [StartDate-Modifier](#modifiers) minus one. At least one Digit | this is rounded down        |
| #~ws00~     | weeks passed, 2 digits          | weeks passed since startDate set by [StartDate-Modifier](#modifiers). Always 2 Digits              | this is rounded down        |
| #~ws00+~    | weeks passed + 1, 2 digits      | weeks passed since startDate set by [StartDate-Modifier](#modifiers) plus 1. Always 2 Digits       | this is rounded down        |
| #~ws00-~    | weeks passed - 1, 2 digits      | weeks passed since startDate set by [StartDate-Modifier](#modifiers) minus 1. Always 2 Digits      | this is rounded down        |
| #~ds~       | days passed                     | days passed since startDate set by [StartDate-Modifier](#modifiers). At least one Digit            | this is rounded down        |
| #~ds+~      | days passed + 1                 | days passed since startDate set by [StartDate-Modifier](#modifiers) plus one. At least one Digit   | this is rounded down        |
| #~ds-~      | days passed - 1                 | days passed since startDate set by [StartDate-Modifier](#modifiers) minus one. At least one Digit  | this is rounded down        |
| #~ds00~     | days passed, 2 digits           | days passed since startDate set by [StartDate-Modifier](#modifiers). Always 2 Digits               | this is rounded down        |
| #~ds00+~    | days passed + 1, 2 digits       | days passed since startDate set by [StartDate-Modifier](#modifiers) plus 1. Always 2 Digits        | this is rounded down        |
| #~ds00-~    | days passed - 1, 2 digits       | days passed since startDate set by [StartDate-Modifier](#modifiers) minus 1. Always 2 Digits       | this is rounded down        |
| #~ds000~    | days passed, 3 digits           | days passed since startDate set by [StartDate-Modifier](#modifiers). Always 3 Digits               | this is rounded down        |
| #~ds000+~   | days passed + 1, 3 digits       | days passed since startDate set by [StartDate-Modifier](#modifiers) plus 1. Always 3 Digits        | this is rounded down        |
| #~ds000-~   | days passed - 1, 3 digits       | days passed since startDate set by [StartDate-Modifier](#modifiers) minus 1. Always 3 Digits       | this is rounded down        |
| #~DATE1~    | today, custom format 1          | current date formatted by format set by [DateFormat1-Modifier](#modifiers)                         | current = day when executed |
| #~DATE2~    | today, custom format 2          | current date formatted by format set by [DateFormat2-Modifier](#modifiers)                         | current = day when executed |
| #~DAY~      | current day                     | current day of the year (1-365)                                                                    | current = day when executed |
| #~DAY00~    | current day, 2 digits           | current day of the year (01-365). Always 2 digits                                                  | current = day when executed |
| #~MONTH~    | current Month                   | current month of the year (1-12)                                                                   | current = day when executed |
| #~MONTH00~  | current Month, 2 digits         | current month of the year (01-12). Always 2 digits                                                 | current = day when executed |
| #~MONTH3~   | name of current Month, 3 Digits | engl. name of current month of the year (Jan-Dec), Always 3 digits                                 | current = day when executed |
| #~YEAR00~   | current year, 2 digits          | current year (e.g. 2004 is 04). Always 2 digits                                                    | current = day when executed |
| #~YEAR0000~ | current year, 4 Digits          | current year (eg 2004), Always 4 digits                                                            | current = day when executed |



# Modifiers
Modifiers are build out of two parts. The abbreviation and a parameter split by ~:  ```name~parameter```. They effect only the values for the line they are used in.

| Name                 | Syntax                    | Short description                                       | Description                                                                                                                                                                                                                  | Example                       |
|----------------------|---------------------------|---------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------|
| StartDate-Modifier   | SDate~StartDate           | Sets start-date for passed-weeks/days-calculation       | Sets the StartDate (ISO-Standard, YYYY-MM-DD) for calculating passed weeks/days                                                                                                                                              | SDate~2022-10-17              |
| Holiday-Modifier     | Holiday~StartDate#EndDate | Ignores Holidays for passed-weeks/days-calculation      | Sets a duration (Iso-Standard, YYYY-MM-DD) that should not be counted when calculating passed weeks/days. Half-week-Holidays do not reduce the passed-weeks-counter (only full weeks count for this counter). DO NOT OVERLAP | Holiday~2023-02-20#2023-04-17 |
| DateFormat1-Modifier | DFor1~FORMAT              | Sets custom dateformat1. (Use ° for spaces)             | Sets the custom dateformat1 used in [#~DATE1~](#urlpath-snippets). "°" is used for spaces (" ").                                                                                                                             | DFor1~DD.MM.YYYY              |
| DateFormat2-Modifier | DFor2~FORMAT              | Sets custom dateformat2. (Use ° for spaces)             | Sets the custom dateformat2 used in [#~DATE2~](#urlpath-snippets). "°" is used for spaces (" ").                                                                                                                             | DFor2~DD°MM°YY                |
| WeekOffset-Modifier  | WOffset~OFFSET            | adds week-offset to passed-week and passed-days-counter | In the end just offsets the StartDate. Use when you need to do more drastic changes to the StartDate that would confuse you if you would do it in the [StartDate-Modifier](#modifiers)                                       | WOffset~-2                    |
| DayOffset-Modifier   | DOffset~OFFSET            | adds days-offset to passed-week and passed-days-counter | In the end just offsets the StartDate. Use when you need to do more drastic changes to the StartDate that would confuse you if you would do it in the [StartDate-Modifier](#modifiers)                                       | DOffset~-5                    |


