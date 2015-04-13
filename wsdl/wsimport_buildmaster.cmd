REM wsimport is failing with the BuildMaster wsdl, sounds like its an issue with wsimport rather than the wsdl.  using this work around:
REM http://stackoverflow.com/questions/13499860/jax-ws-error-on-wsdl-file-error-resolving-component-sschema
REM
REM Here's some gradle specific alternatives to this manual import process:
REM https://github.com/jacobono/gradle-wsdl-plugin
REM http://blog.jstrgames.com/2014/03/08/gradle-plug-in-wsdl-to-java-code-generation/
REM http://jaxenter.com/tutorial-gradle-soap-features-revealed-104493.html
REM http://stackoverflow.com/questions/8158453/howto-generate-classes-from-wsdl-and-xsd-with-gradle-equivalent-to-maven-jaxb2

%java_home%\bin\wsimport BuildMaster.wsdl -b http://www.w3.org/2001/XMLSchema.xsd -b customization.xjb -s ..\src\main\generated -Xnocompile
pause