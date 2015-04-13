
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
 *         &lt;element name="Application_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Release_Number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Workflow_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Target_Date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Release_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Notes_Text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeployableIds_Xml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "applicationId",
    "releaseNumber",
    "workflowId",
    "targetDate",
    "releaseName",
    "notesText",
    "deployableIdsXml"
})
@XmlRootElement(name = "Releases_CreateOrUpdateRelease")
public class ReleasesCreateOrUpdateRelease {

    protected String apiKey;
    @XmlElement(name = "Application_Id", required = true, type = Integer.class, nillable = true)
    protected Integer applicationId;
    @XmlElement(name = "Release_Number")
    protected String releaseNumber;
    @XmlElement(name = "Workflow_Id", required = true, type = Integer.class, nillable = true)
    protected Integer workflowId;
    @XmlElement(name = "Target_Date", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar targetDate;
    @XmlElement(name = "Release_Name")
    protected String releaseName;
    @XmlElement(name = "Notes_Text")
    protected String notesText;
    @XmlElement(name = "DeployableIds_Xml")
    protected String deployableIdsXml;

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
     * Gets the value of the applicationId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getApplicationId() {
        return applicationId;
    }

    /**
     * Sets the value of the applicationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setApplicationId(Integer value) {
        this.applicationId = value;
    }

    /**
     * Gets the value of the releaseNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReleaseNumber() {
        return releaseNumber;
    }

    /**
     * Sets the value of the releaseNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReleaseNumber(String value) {
        this.releaseNumber = value;
    }

    /**
     * Gets the value of the workflowId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWorkflowId() {
        return workflowId;
    }

    /**
     * Sets the value of the workflowId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWorkflowId(Integer value) {
        this.workflowId = value;
    }

    /**
     * Gets the value of the targetDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTargetDate() {
        return targetDate;
    }

    /**
     * Sets the value of the targetDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTargetDate(XMLGregorianCalendar value) {
        this.targetDate = value;
    }

    /**
     * Gets the value of the releaseName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReleaseName() {
        return releaseName;
    }

    /**
     * Sets the value of the releaseName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReleaseName(String value) {
        this.releaseName = value;
    }

    /**
     * Gets the value of the notesText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotesText() {
        return notesText;
    }

    /**
     * Sets the value of the notesText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotesText(String value) {
        this.notesText = value;
    }

    /**
     * Gets the value of the deployableIdsXml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeployableIdsXml() {
        return deployableIdsXml;
    }

    /**
     * Sets the value of the deployableIdsXml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeployableIdsXml(String value) {
        this.deployableIdsXml = value;
    }

}
