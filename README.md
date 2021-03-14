# workshop-ranked-choice-selection

## Notes

### Data Format

Files should be imported as Tab-Separated Values files to avoid issues with commas being present in Workshop descriptions.

Workshop names in the TSV file need to match the options in the form given to students.

Workshop spreadsheet format should be:
- Column A: ID
- Column B: Name
- Column C: Description
- Column D: URL
- Column E, Faculty Moderators (separated by commas)
- Column F, Presenters (separated by commas)
- Column G, Type (either TALK or SEMINAR)
- Column H, Sessions (either A, B, or AB)

## TODO

- Change CSVReader to CSVReaderBuilder so that TSV files are supported (see https://sourceforge.net/p/opencsv/bugs/205/)