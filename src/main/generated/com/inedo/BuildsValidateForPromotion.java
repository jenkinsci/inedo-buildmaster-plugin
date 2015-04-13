
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
 *         &lt;element name="ForcePromotion_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DependencyContingent_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PromoteTo_Environment_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "forcePromotionIndicator",
    "dependencyContingentIndicator",
    "promoteToEnvironmentId"
})
@XmlRootElement(name = "Builds_ValidateForPromotion")
public class BuildsValidateForPromotion {

    protected String apiKey;
    @XmlElement(name = "Application_Id", required = true, type = Integer.class, nillable = true)
    protected Integer applicationId;
    @XmlElement(name = "Release_Number")
    protected String releaseNumber;
    @XmlElement(name = "Build_Number")
    protected String buildNumber;
    @XmlElement(name = "ForcePromotion_Indicator")
    protected String forcePromotionIndicator;
    @XmlElement(name = "DependencyContingent_Indicator")
    protected String dependencyContingentIndicator;
    @XmlElement(name = "PromoteTo_Environment_Id", required = true, type = Integer.class, nillable = true)
    protected Integer promoteToEnvironmentId;

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
     * Gets the value of the forcePromotionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForcePromotionIndicator() {
        return forcePromotionIndicator;
    }

    /**
     * Sets the value of the forcePromotionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForcePromotionIndicator(String value) {
        this.forcePromotionIndicator = value;
    }

    /**
     * Gets the value of the dependencyContingentIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDependencyContingentIndicator() {
        return dependencyContingentIndicator;
    }

    /**
     * Sets the value of the dependencyContingentIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDependencyContingentIndicator(String value) {
        this.dependencyContingentIndicator = value;
    }

    /**
     * Gets the value of the promoteToEnvironmentId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPromoteToEnvironmentId() {
        return promoteToEnvironmentId;
    }

    /**
     * Sets the value of the promoteToEnvironmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPromoteToEnvironmentId(Integer value) {
        this.promoteToEnvironmentId = value;
    }

}
