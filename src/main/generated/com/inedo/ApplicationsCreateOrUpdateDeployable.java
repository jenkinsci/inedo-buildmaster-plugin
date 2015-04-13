
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
 *         &lt;element name="Deployable_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeployableType_Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "deployableName",
    "deployableTypeCode"
})
@XmlRootElement(name = "Applications_CreateOrUpdateDeployable")
public class ApplicationsCreateOrUpdateDeployable {

    protected String apiKey;
    @XmlElement(name = "Application_Id", required = true, type = Integer.class, nillable = true)
    protected Integer applicationId;
    @XmlElement(name = "Deployable_Name")
    protected String deployableName;
    @XmlElement(name = "DeployableType_Code")
    protected String deployableTypeCode;

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
     * Gets the value of the deployableName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeployableName() {
        return deployableName;
    }

    /**
     * Sets the value of the deployableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeployableName(String value) {
        this.deployableName = value;
    }

    /**
     * Gets the value of the deployableTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeployableTypeCode() {
        return deployableTypeCode;
    }

    /**
     * Sets the value of the deployableTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeployableTypeCode(String value) {
        this.deployableTypeCode = value;
    }

}
