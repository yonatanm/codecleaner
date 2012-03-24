# CodeCleaner

## What is it ?
**CodeCleaner** is a Maven plugin which allows you control eclipse setting for all your maven based projects.

## Type of settings

- Code Formatter
- Save Actions
- Clean up settings
- Compiler Error/Warning level
- JSP/XML/XSD/HTML Validation

## How to use ? 

### use codecleaner from central repository

#### in your main pom.xm file add:

    <plugins>
      ....
        <plugin>
        <groupId>com.outbrain</groupId>
        <artifactId>codecleaner-maven-plugin</artifactId>
        <version>1.0.2</version>
        <configuration>
          <corePrefsFile>devtools/eclipse/settings/org.eclipse.jdt.core.prefs</corePrefsFile>
          <uiPrefsFile>devtools/eclipse/settings/org.eclipse.jdt.ui.prefs</uiPrefsFile>
          <validationPrefsFile>devtools/eclipse/settings/org.eclipse.wst.validation.prefs</validationPrefsFile>
          <htmlValidationPrefsFile>devtools/eclipse/settings/org.eclipse.wst.html.core.prefs</htmlValidationPrefsFile>
          <jspValidationPrefsFile>devtools/eclipse/settings/org.eclipse.jst.jsp.core.prefs</jspValidationPrefsFile>
          <findbugsFile>devtools/eclipse/settings/.fbprefs</findbugsFile>
          <pmdFileName>devtools/eclipse/settings/.pmd</pmdFileName>
          <pmdRuleFileName>devtools/eclipse/settings/.ruleset</pmdRuleFileName>
        </configuration>
      </plugin>
      ....
    </plugins>

#### and run

- set formatter, save action and code cleanup settings:


    $ mvn com.outbrain:codecleaner-maven-plugin:style

    $ mvn com.outbrain:codecleaner-maven-plugin:compiler

    $ mvn com.outbrain:codecleaner-maven-plugin:validation


under devtools/eclipse/settings/ put the eclipse settings file (as taken frm one of your well-configured-project)
org.eclipse.jdt.core.prefs - error/warning level and formatter settings
org.eclipse.jdt.ui.prefs - cleanup and save action settings
org.eclipse.wst.validation.prefs - main validation settings
org.eclipse.wst.html.core.prefs - HTML validation settings
org.eclipse.jst.jsp.core.prefs - JSP validation settings
.fbprefs - find bugs settings (not fully supported)
.pmd - PMD settings (not fully supported)
.ruleset - PMD rule sets (not fully supported)
