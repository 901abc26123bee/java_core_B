package com.wong.exception;

/*
java 異常
java.lang.Throwable
## 分類
* Error 錯誤，程序中不進行處理
* Exception 異常，要求在編寫程序時，就要考慮到這些異常的處理
## Exception
* 編譯時異常，在編譯期間會出現的異常（執行javac 命令時，出現異常）
    Exception的子類中除了RuntimeException以外的
* 運行時異常（執行java命令時，出現異常）
    RuntimeException的所有子類
## 異常特點
* 執行一個程序時，如果出現異常，那麼異常後面的代碼就不再執行
* 
* ## Error
AnnotationFormatError,
AssertionError,
AWTError,
CoderMalfunctionError,
FactoryConfigurationError,
FactoryConfigurationError,
IOError,
LinkageError,
SchemaFactoryConfigurationError,
ServiceConfigurationError,
ThreadDeath,
TransformerFactoryConfigurationError,
VirtualMachineError
## Exception
### 编译时异常
AclNotFoundException,
ActivationException,
AlreadyBoundException,
ApplicationException,
AWTException,
BackingStoreException,
BadAttributeValueExpException,
BadBinaryOpValueExpException,
BadLocationException,
BadStringOperationException,
BrokenBarrierException,
CertificateException,
CloneNotSupportedException,
DataFormatException,
DatatypeConfigurationException,
DestroyFailedException,
ExecutionException,
ExpandVetoException,
FontFormatException,
GeneralSecurityException,
GSSException,
IllegalClassFormatException,
InterruptedException,
IntrospectionException,
InvalidApplicationException,
InvalidMidiDataException,
InvalidPreferencesFormatException,
InvalidTargetObjectTypeException,
IOException,
JAXBException,
JMException,
KeySelectorException,
LambdaConversionException,
LastOwnerException,
LineUnavailableException,
MarshalException,
MidiUnavailableException,
MimeTypeParseException,
MimeTypeParseException,
NamingException,
NoninvertibleTransformException,
NotBoundException,
NotOwnerException,
ParseException,
ParserConfigurationException,
PrinterException,
PrintException,
PrivilegedActionException,
PropertyVetoException,
ReflectiveOperationException,
RefreshFailedException,
RemarshalException,
SAXException,
ScriptException,
ServerNotActiveException,
SOAPException,
SQLException,
TimeoutException,
TooManyListenersException,
TransformerException,
TransformException,
UnmodifiableClassException,
UnsupportedAudioFileException,
UnsupportedCallbackException,
UnsupportedFlavorException,
UnsupportedLookAndFeelException,
URIReferenceException,
URISyntaxException,
UserException,
XAException,
XMLParseException,
XMLSignatureException,
XMLStreamException,
XPathException
### 运行时异常
AnnotationTypeMismatchException,
ArithmeticException,
ArrayStoreException,
BufferOverflowException,
BufferUnderflowException,
CannotRedoException,
CannotUndoException,
ClassCastException,
CMMException,
CompletionException,
ConcurrentModificationException,
DataBindingException,
DateTimeException,
DOMException,
EmptyStackException,
EnumConstantNotPresentException,
EventException,
FileSystemAlreadyExistsException,
FileSystemNotFoundException,
IllegalArgumentException,
IllegalMonitorStateException,
IllegalPathStateException,
IllegalStateException,
IllformedLocaleException,
ImagingOpException,
IncompleteAnnotationException,
IndexOutOfBoundsException,
JMRuntimeException,
LSException,
MalformedParameterizedTypeException,
MalformedParametersException,
MirroredTypesException,
MissingResourceException,
NegativeArraySizeException,
NoSuchElementException,
NoSuchMechanismException,
NullPointerException,
ProfileDataException,
ProviderException,
ProviderNotFoundException,
RasterFormatException,
RejectedExecutionException,
SecurityException,
SystemException,
TypeConstraintException,
TypeNotPresentException,
UncheckedIOException,
UndeclaredThrowableException,
UnknownEntityException,
UnmodifiableSetException,
UnsupportedOperationException,
WebServiceException,
WrongMethodTypeException
* */

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class ExceptionTest {

    // InputMismatchException 輸入類型不匹配
    @Test
    public void test1() {
        Scanner sc = new Scanner(System.in);
        System.out.println("請輸入一個整數：");
        int i16 = sc.nextInt(); // 當輸入的不是整數時報錯
        System.out.println(i16);
        sc.close();
    }

    // ArrayIndexOutOfBoundsException 數組下標越界
    @Test
    public void test2() {
        int[] iarr = new int[3];
        System.out.println(iarr[3]);
    }

    // ArithmeticException 算術異常
    @Test
    public void test3() {
        System.out.println(10/0);
    }

    // ClassCastException 類轉換異常
    @Test
    public void Test4() {
        Object b = true;
        String s = (String) b;
        System.out.println(s);
    }

    // NullPointerException 空指針異常
    @Test
    public void Test5() {
        Person p1 = new Person();
        p1 = null;
        System.out.println(p1.toString());

        System.out.println("abc");
    }

    // 編譯時異常
    @Test
    public void test6() throws Exception { // 為讓編譯通過，可拋出錯誤 public void test6() {
        FileInputStream fp = new FileInputStream(new File("hello.txt"));
        int b;
        while ((b = fp.read()) != -1) {
            System.out.println((char) b);
        }
        fp.close();
    }
}

class Person {
    private String name;

}

