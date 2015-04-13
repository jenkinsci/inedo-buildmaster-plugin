
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
 *         &lt;element name="Eligible_Indicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IneligibleReason_Text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "eligibleIndicator",
    "ineligibleReasonText"
})
@XmlRootElement(name = "Builds_ValidateForPromotionResponse")
public class BuildsValidateForPromotionResponse {

    @XmlElement(name = "Eligible_Indicator")
    protected String eligibleIndicator;
    @XmlElement(name = "IneligibleReason_Text")
    protected String ineligibleReasonText;

    /**
     * Gets the value of the eligibleIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEligibleIndicator() {
        return eligibleIndicator;
    }

    /**
     * Sets the value of the eligibleIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEligibleIndicator(String value) {
        this.eligibleIndicator = value;
    }

    /**
     * Gets the value of the ineligibleReasonText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIneligibleReasonText() {
        return ineligibleReasonText;
    }

    /**
     * Sets the value of the ineligibleReasonText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIneligibleReasonText(String value) {
        this.ineligibleReasonText = value;
    }

}
