package net.objectzoo.ccd.sla;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by tilmann on 08.06.2016.
 */
public class ObjectToJsonTest
{
    private ObjectToJson sut = new ObjectToJson();

    @Test
    public void serializesStringProperty()
    {
        Object bean = new Object()
        {
            public String getStringProperty()
            {
                return "testString";
            }
        };

        String json = sut.objectToJson(bean);

        json = json.replaceAll("\\s", "");
        assertThat(json, is("{stringProperty:\"testString\"}"));
    }

    @Test
    public void serializesDoubleProperty()
    {
        Object bean = new Object()
        {
            public double getDoubleProperty()
            {
                return 1.2345d;
            }
        };

        String json = sut.objectToJson(bean);

        json = json.replaceAll("\\s", "");
        assertThat(json, is("{doubleProperty:1.2345}"));
    }

    @Test
    public void serializesNullProperty()
    {
        Object bean = new Object()
        {
            public Object getNullProperty()
            {
                return null;
            }
        };

        String json = sut.objectToJson(bean);

        json = json.replaceAll("\\s", "");
        assertThat(json, is("{nullProperty:null}"));
    }

    @Test
    public void serializesBeanProperty()
    {
        Object bean1 = new Object()
        {

            public String getStringProperty()
            {
                return "testString";
            }
        };

        Object bean2 = new Object()
        {
            public Object getBeanProperty()
            {
                return bean1;
            }
        };

        String json = sut.objectToJson(bean2);

        json = json.replaceAll("\\s", "");
        assertThat(json, is("{beanProperty:{stringProperty:\"testString\"}}"));
    }

}
