import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.aaron.protobuf.AddressBookProtos.Person;

;

/**
 * Test protobuf
 * Created by Aaron.Qiu on 2017/5/13.
 */
public class TestPersonAddress {
    private Person person;
    private byte[] pbytes;
    private FileOutputStream fos;
    private FileInputStream fis;

    @Before
    public void before() throws FileNotFoundException {
        fos = new FileOutputStream("personAddr");
        person =
                Person.newBuilder()
                        .setId(1234)
                        .setName("John Doe")
                        .setEmail("jdoe@example.com")
                        .addPhones(
                                Person.PhoneNumber.newBuilder()
                                        .setNumber("555-4321")
                                        .setType(Person.PhoneType.HOME))
                        .build();
        pbytes = person.toByteArray();
        try {
            person.writeTo(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fis = new FileInputStream("personAddr");
    }

    @After
    public void after() throws IOException {
        fos.close();
        fis.close();
    }

    @Test
    public void testBuildPerson() {

        System.out.println(person.toString());
        //checks if all the required fields have been set.
        Assert.assertTrue(person.isInitialized());
        Assert.assertNotNull(person);
    }

    @Test
    public void testParsePersonFromBytes() {
        try {
            Person p = Person.parseFrom(pbytes);
            System.out.println(p.toString());
            //checks if all the required fields have been set.
            Assert.assertTrue(p.isInitialized());
            Assert.assertNotNull(p);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testParsePersonFromFileInputStream() throws IOException {
        try {

            Person p2 = Person.parseFrom(fis);
            System.out.println(p2.toString());
            //checks if all the required fields have been set.
            Assert.assertTrue(p2.isInitialized());
            Assert.assertNotNull(p2);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

}
