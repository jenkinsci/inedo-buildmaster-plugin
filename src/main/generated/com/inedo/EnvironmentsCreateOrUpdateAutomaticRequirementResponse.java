
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
 *         &lt;element name="Requirement_Sequence" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "requirementSequence"
})
@XmlRootElement(name = "Environments_CreateOrUpdateAutomaticRequirementResponse")
public class EnvironmentsCreateOrUpdateAutomaticRequirementResponse {

    @XmlElement(name = "Requirement_Sequence", required = true, type = Integer.class, nillable = true)
    protected Integer requirementSequence;

    /**
     * Gets the value of the requirementSequence property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRequirementSequence() {
        return requirementSequence;
    }

    /**
     * Sets the value of the requirementSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRequirementSequence(Integer value) {
        this.requirementSequence = value;
    }

}
