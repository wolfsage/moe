package org.moe.runtime

import org.moe.Moe.Errors

import scala.collection.mutable.HashMap

class MoeObject {

    private val id : Integer = System.identityHashCode( this )
    private val data = new HashMap[String,MoeObject]()

    private var klass : MoeClass = _    

    def this ( k : MoeClass ) = {
        this()
        klass = k
    }

    def getID              (): Integer  = id
    def getAssociatedClass (): MoeClass = klass
    def hasAssociatedClass (): Boolean  = klass != null

    def setAssocaitedClass ( k : MoeClass ): Unit = klass = k

    def hasValue ( name : String ): Boolean   = data.contains( name )
    def getValue ( name : String ): MoeObject = {
        if ( !hasValue( name ) ) throw new Errors.InstanceValueNotFound( name )
        data( name )
    }

    def setValue ( name : String, value : MoeObject ): Unit = data.put( name, value )

    def callMethod ( method : String, args : List[MoeObject] ): MoeObject = {
        if ( klass == null ) throw new Errors.MissingClass( toString )
        klass.getMethod( method ).call( this, args )
    }

    override def toString (): String = {
        var out = "{ #instance(" + id + ")"
        if ( hasAssociatedClass ) {
            out += " " + klass.toString() 
        }
        out + " }"
    }
}