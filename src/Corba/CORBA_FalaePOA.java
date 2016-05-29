package Corba;


/**
* Corba/CORBA_FalaePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from CORBA_module.idl
* S�bado, 28 de Maio de 2016 21h19min18s BRT
*/

public abstract class CORBA_FalaePOA extends org.omg.PortableServer.Servant
 implements Corba.CORBA_FalaeOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getUsers", new java.lang.Integer (0));
    _methods.put ("getLogs", new java.lang.Integer (1));
    _methods.put ("postLog", new java.lang.Integer (2));
    _methods.put ("generateToken", new java.lang.Integer (3));
    _methods.put ("verifyToken", new java.lang.Integer (4));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // Corba/CORBA_Falae/getUsers
       {
         String $result = null;
         $result = this.getUsers ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // Corba/CORBA_Falae/getLogs
       {
         String $result = null;
         $result = this.getLogs ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 2:  // Corba/CORBA_Falae/postLog
       {
         String userName = in.read_string ();
         String token = in.read_string ();
         String dateTime = in.read_string ();
         String $result = null;
         $result = this.postLog (userName, token, dateTime);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 3:  // Corba/CORBA_Falae/generateToken
       {
         String userName = in.read_string ();
         String userPassword = in.read_string ();
         String $result = null;
         $result = this.generateToken (userName, userPassword);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 4:  // Corba/CORBA_Falae/verifyToken
       {
         String token = in.read_string ();
         boolean $result = false;
         $result = this.verifyToken (token);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Corba/CORBA_Falae:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public CORBA_Falae _this() 
  {
    return CORBA_FalaeHelper.narrow(
    super._this_object());
  }

  public CORBA_Falae _this(org.omg.CORBA.ORB orb) 
  {
    return CORBA_FalaeHelper.narrow(
    super._this_object(orb));
  }


} // class CORBA_FalaePOA