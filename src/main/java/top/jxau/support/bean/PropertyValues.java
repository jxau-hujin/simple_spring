package top.jxau.support.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author plutohh
 */
public class PropertyValues {

    private List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String key) {
        for(PropertyValue propertyValue : this.propertyValueList) {
            if(propertyValue.equals(key)) {
                return propertyValue;
            }
        }
        return null;
    }
}
