
package com.inedo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Build_Number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Environment_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Approval_Sequence" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="User_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GroupNames_List" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Comments_Text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "buildNumber",
    "environmentId",
    "approvalSequence",
    "userName",
    "groupNamesList",
    "commentsText"
})
@XmlRootElement(name = "Builds_AddApproval")
public class BuildsAddApproval {

    protected String apiKey;
    @XmlElement(name = "Application_Id", required = true, type = Integer.class, nillable = true)
    protected Integer applicationId;
    @XmlElement(name = "Release_Number")
    protected String releaseNumber;
    @XmlElement(name = "Build_Number")
    protected String buildNumber;
    @XmlElement(name = "Environment_Id", required = true, type = Integer.class, nillable = true)
    protected Integer environmentId;
    @XmlElement(name = "Approval_Sequence", required = true, type = Integer.class, nillable = true)
    protected Integer approvalSequence;
    @XmlElement(name = "User_Name")
    protected String userName;
    @XmlElement(name = "GroupNames_List")
    protected String groupNamesList;
    @XmlElement(name = "Comments_Text")
    protected String commentsText;

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
     * Gets the value of the buildNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildNumber() {
        return buildNumber;
    }

    /**
     * Sets the value of the buildNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildNumber(String value) {
        this.buildNumber = value;
    }

    /**
     * Gets the value of the environmentId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEnvironmentId() {
        return environmentId;
    }

    /**
     * Sets the value of the environmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEnvironmentId(Integer value) {
        this.environmentId = value;
    }

    /**
     * Gets the value of the approvalSequence property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getApprovalSequence() {
        return approvalSequence;
    }

    /**
     * Sets the value of the approvalSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setApprovalSequence(Integer value) {
        this.approvalSequence = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the groupNamesList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupNamesList() {
        return groupNamesList;
    }

    /**
     * Sets the value of the groupNamesList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupNamesList(String value) {
        this.groupNamesList = value;
    }

    /**
     * Gets the value of the commentsText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommentsText() {
        return commentsText;
    }

    /**
     * Sets the value of the commentsText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommentsText(String value) {
        this.commentsText = value;
    }

}
