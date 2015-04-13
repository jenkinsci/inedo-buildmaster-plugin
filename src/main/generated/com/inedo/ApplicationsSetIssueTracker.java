
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
 *         &lt;element name="IssueTracking_Provider_Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IssueTracking_CategoryIdList_Text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "issueTrackingProviderId",
    "issueTrackingCategoryIdListText"
})
@XmlRootElement(name = "Applications_SetIssueTracker")
public class ApplicationsSetIssueTracker {

    protected String apiKey;
    @XmlElement(name = "Application_Id", required = true, type = Integer.class, nillable = true)
    protected Integer applicationId;
    @XmlElement(name = "IssueTracking_Provider_Id", required = true, type = Integer.class, nillable = true)
    protected Integer issueTrackingProviderId;
    @XmlElement(name = "IssueTracking_CategoryIdList_Text")
    protected String issueTrackingCategoryIdListText;

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
     * Gets the value of the issueTrackingProviderId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIssueTrackingProviderId() {
        return issueTrackingProviderId;
    }

    /**
     * Sets the value of the issueTrackingProviderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIssueTrackingProviderId(Integer value) {
        this.issueTrackingProviderId = value;
    }

    /**
     * Gets the value of the issueTrackingCategoryIdListText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueTrackingCategoryIdListText() {
        return issueTrackingCategoryIdListText;
    }

    /**
     * Sets the value of the issueTrackingCategoryIdListText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueTrackingCategoryIdListText(String value) {
        this.issueTrackingCategoryIdListText = value;
    }

}
