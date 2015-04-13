
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
 *         &lt;element name="LinkAllActionGroups_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LinkAllDeploymentPlans_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="New_Application_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "linkAllActionGroupsIndicator",
    "linkAllDeploymentPlansIndicator",
    "newApplicationName"
})
@XmlRootElement(name = "Applications_CloneApplication")
public class ApplicationsCloneApplication {

    protected String apiKey;
    @XmlElement(name = "Application_Id", required = true, type = Integer.class, nillable = true)
    protected Integer applicationId;
    @XmlElement(name = "LinkAllActionGroups_Indicator")
    protected String linkAllActionGroupsIndicator;
    @XmlElement(name = "LinkAllDeploymentPlans_Indicator")
    protected String linkAllDeploymentPlansIndicator;
    @XmlElement(name = "New_Application_Name")
    protected String newApplicationName;

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
     * Gets the value of the linkAllActionGroupsIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkAllActionGroupsIndicator() {
        return linkAllActionGroupsIndicator;
    }

    /**
     * Sets the value of the linkAllActionGroupsIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkAllActionGroupsIndicator(String value) {
        this.linkAllActionGroupsIndicator = value;
    }

    /**
     * Gets the value of the linkAllDeploymentPlansIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkAllDeploymentPlansIndicator() {
        return linkAllDeploymentPlansIndicator;
    }

    /**
     * Sets the value of the linkAllDeploymentPlansIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkAllDeploymentPlansIndicator(String value) {
        this.linkAllDeploymentPlansIndicator = value;
    }

    /**
     * Gets the value of the newApplicationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewApplicationName() {
        return newApplicationName;
    }

    /**
     * Sets the value of the newApplicationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewApplicationName(String value) {
        this.newApplicationName = value;
    }

}
