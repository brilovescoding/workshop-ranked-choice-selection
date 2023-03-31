# Workshop Scheduler via Ranked Choice

## Summary
This application is designed to take a dataset of Attendees to a set of Workshops, and schedule them into each workshop by adhering to their preferences as closely as possible. The users will feed in two CSV files, attendees and workshops, and it returns three CSV files: everyone who was scheduled, workshop attendance, and the "leftovers": attendees who were unable to be scheduled.

The application supports having "Free Talks" - workshops where the attendance is uncapped - so that attendees who were unable to be placed within their preferences (because they chose a very popular workshop) can be scheduled in them. Because this happens automatically, ideally the leftovers file is empty, but it is useful in terms of debugging or revealing scheduling issues.

## Proposed Features
[ ] Schedule for as many preferences as needed

[ ] More flexible parsing of the CSV files

[ ] Make all workshop fields customizable via a frontend interface

## Data Format

Files should be imported as Tab-Separated Values files to avoid issues with commas being present in Workshop descriptions.

Workshop names in the TSV file need to match the options in the form given to students.

Attendee spreadsheet format should be:
- Column A: Name
- Column B: Grade
- Column C: Email Address
- Columns D-H: 1st-5th preferences

Workshop spreadsheet format should be:
- Column A: ID
- Column B: Name
- Column C: Description
- Column D: URL
- Column E, Faculty Moderators (separated by commas)
- Column F, Presenters (separated by commas)
- Column G, Type (either TALK or SEMINAR)
- Column H, Sessions (either A, B, or AB)

The resultant spreadsheet, a list of attendees, will be formatted as follows:
- Column A: Email Address
- Column B: Attendee Name
- Column C: Attendee Grade
- Column D: Attendee's Workshop A name and URL
- Column E: Attendee's Workshop B name and URL

## TODO:
- Sort leftover students into the MOST populated workshop instead of the least populated
- 8th graders should be not scheduled for Workshop A - create a way to account for this