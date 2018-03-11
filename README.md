# Projectory

This is cards generator, Java implementation.

Running is as simple as:
```
java -jar Projectory.jar
```

Incident cards will be read from current directory, and PDF output will be generated in current directory.

If `incidents.json` file does not exist - the default one will be generated in current directory.

**NOTE:** due to nature of Batik and PDFBox libraries, this application uses excessive amount of RAM. Hopefully the situation will improve eventually, or generator will be implemented in different programming language with better PDF support.
