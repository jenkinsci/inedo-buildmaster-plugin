
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
 *         &lt;element name="Value_Text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "valueText"
})
@XmlRootElement(name = "Configuration_GetValueResponse")
public class ConfigurationGetValueResponse {

    @XmlElement(name = "Value_Text")
    protected String valueText;

    /**
     * Gets the value of the valueText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueText() {
        return valueText;
    }

    /**
     * Sets the value of the valueText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueText(String value) {
        this.valueText = value;
    }

}
