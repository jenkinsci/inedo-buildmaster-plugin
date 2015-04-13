
package com.inedo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apiKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BuildExecution_PlanAction_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Group_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Test_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TestPassed_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TestResult_Text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TestStarted_Date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="TestEnded_Date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "apiKey",
    "buildExecutionPlanActionId",
    "groupName",
    "testName",
    "testPassedIndicator",
    "testResultText",
    "testStartedDate",
    "testEndedDate"
})
@XmlRootElement(name = "BuildTestResults_RecordTestResult")
public class BuildTestResultsRecordTestResult {

    protected String apiKey;
    @XmlElement(name = "BuildExecution_PlanAction_Id", required = true, type = Integer.class, nillable = true)
    protected Integer buildExecutionPlanActionId;
    @XmlElement(name = "Group_Name")
    protected String groupName;
    @XmlElement(name = "Test_Name")
    protected String testName;
    @XmlElement(name = "TestPassed_Indicator")
    protected String testPassedIndicator;
    @XmlElement(name = "TestResult_Text")
    protected String testResultText;
    @XmlElement(name = "TestStarted_Date", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar testStartedDate;
    @XmlElement(name = "TestEnded_Date", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar testEndedDate;

    /**
     * Gets the value of the apiKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets the value of the apiKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApiKey(String value) {
        this.apiKey = value;
    }

    /**
     * Gets the value of the buildExecutionPlanActionId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBuildExecutionPlanActionId() {
        return buildExecutionPlanActionId;
    }

    /**
     * Sets the value of the buildExecutionPlanActionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBuildExecutionPlanActionId(Integer value) {
        this.buildExecutionPlanActionId = value;
    }

    /**
     * Gets the value of the groupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the value of the groupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupName(String value) {
        this.groupName = value;
    }

    /**
     * Gets the value of the testName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestName() {
        return testName;
    }

    /**
     * Sets the value of the testName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestName(String value) {
        this.testName = value;
    }

    /**
     * Gets the value of the testPassedIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestPassedIndicator() {
        return testPassedIndicator;
    }

    /**
     * Sets the value of the testPassedIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestPassedIndicator(String value) {
        this.testPassedIndicator = value;
    }

    /**
     * Gets the value of the testResultText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestResultText() {
        return testResultText;
    }

    /**
     * Sets the value of the testResultText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestResultText(String value) {
        this.testResultText = value;
    }

    /**
     * Gets the value of the testStartedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTestStartedDate() {
        return testStartedDate;
    }

    /**
     * Sets the value of the testStartedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTestStartedDate(XMLGregorianCalendar value) {
        this.testStartedDate = value;
    }

    /**
     * Gets the value of the testEndedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTestEndedDate() {
        return testEndedDate;
    }

    /**
     * Sets the value of the testEndedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTestEndedDate(XMLGregorianCalendar value) {
        this.testEndedDate = value;
    }

}
