
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
 *         &lt;element name="DeploymentPlan_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IncludeActionUsage_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "deploymentPlanId",
    "includeActionUsageIndicator"
})
@XmlRootElement(name = "Plans_GetDeploymentPlan")
public class PlansGetDeploymentPlan {

    protected String apiKey;
    @XmlElement(name = "DeploymentPlan_Id", required = true, type = Integer.class, nillable = true)
    protected Integer deploymentPlanId;
    @XmlElement(name = "IncludeActionUsage_Indicator")
    protected String includeActionUsageIndicator;

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
     * Gets the value of the deploymentPlanId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDeploymentPlanId() {
        return deploymentPlanId;
    }

    /**
     * Sets the value of the deploymentPlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDeploymentPlanId(Integer value) {
        this.deploymentPlanId = value;
    }

    /**
     * Gets the value of the includeActionUsageIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncludeActionUsageIndicator() {
        return includeActionUsageIndicator;
    }

    /**
     * Sets the value of the includeActionUsageIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncludeActionUsageIndicator(String value) {
        this.includeActionUsageIndicator = value;
    }

}
