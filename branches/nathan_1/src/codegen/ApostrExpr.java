package codegen;

import compiler.SymbolTable;
import lexer.*;

/**
 * Arithmetic Expressions, extend Expr
 */
public class ApostrExpr extends Expr {
    
    /**
     * Constructor - Takes an expression and an identifier
     * @param expr
     * @param ident
     */
    public ApostrExpr(Expr objexpr, Expr attexpr) {
        this.objexpr = objexpr;
        this.attexpr = attexpr;
    }
    
    @Override
    public String code(SymbolTable table) {
    	Type atttype = attexpr.getType(table);
    	Type objtype = objexpr.getType(table);
    	String attcode = attexpr.code(table);
    	String objcode = objexpr.code(table);
    	
    	/* The requested attribute is a teamStat */
    	if (atttype.equals(Type.teamStat)) {
    		if (!objtype.equals(Type.team)) {
    			throw new RuntimeException("objexpr: type mismatch " + objtype + " and " + atttype);
    		}
    		return "TeamObj." + attcode + ".get(" + objcode + ");";		//TeamObj.statname.get(teamname);
    	}
    	/* The requested attribute is a playerStat */
    	if (atttype.equals(Type.playerStat)) {
    		if (!objtype.equals(Type.player)) {
    			throw new RuntimeException("objexpr: type mismatch " + objtype + " and " + atttype);
    		}
    		return "PlayerObj." + attcode + ".get(" + objcode + ");";	//PlayerObj.statname.get(playername);
    	}
    	else 
    		throw new RuntimeException("Expr: type" + atttype + "unsuitable for attribute retrieval");
    }
    
    @Override
    public Type getType(SymbolTable table) {
        Type atttype = attexpr.getType(table);
        Type objtype = objexpr.getType(table);
        
        if (atttype.equals(Type.teamStat)) {
            return Type.teamStat;
        }
        
        if (atttype.equals(Type.playerStat)) {
        	return Type.playerStat;
        }
        
        if (atttype.equals(Type.string)) {
        	return Type.string;
        }
        
        if ((atttype.equals(Type.teamStat)) && (!objtype.equals(Type.team))) {
            throw new RuntimeException("objexpr: type mismatch " + objtype + " and " + atttype);
        }
        
        if ((atttype.equals(Type.playerStat)) && (!objtype.equals(Type.player))) {
        	throw new RuntimeException("objexpr: type mismatch" + objtype + " and " + atttype);
        }
        else 
    		throw new RuntimeException("Expr: type" + atttype + "unsuitable for attribute retrieval");
    }
    
    /*
    @Override
    public String code(SymbolTable table) {
        String lcode = valueL.code(table);
        String rcode = valueR.code(table);
        Type ltype = valueL.getType(table);
        Type rtype = valueR.getType(table);
        
        /* STRING CONCATENATION 
        if (ltype.equals(Type.string)) {
            // convert r to string
            if (rtype.equals(Type.number))
                return lcode + ".concat(Float.toString(" + rcode + "))";
            return lcode + ".concat((" + rcode + ").toString())";
        }
        if (rtype.equals(Type.string)) {
            // convert r to string
            if (ltype.equals(Type.number))
                return "Float.toString(" + lcode + ").concat(" + rcode + ")";
            return lcode + ".toString().concat(" + rcode + ")";
        }
        
    	if(! ltype.equals(rtype)) {
    		throw new RuntimeException("objexpr: type mismatch " + valueL.getType(table) + " and " + valueR.getType(table));
    	}
    	if (ltype.equals(Type.number)) {
            /* Number addition 
            String result = lcode;
    		result += " " + getOpCode() + " ";
    		result += rcode;
    		return result;
        } else if (ltype instanceof ListType) {
            /* List append 
            String result = "(" + lcode + ").append(";
            result += rcode;
            return result + ")";
        } else {
            throw new RuntimeException("objexpr: type " + ltype + " unsuitable for addition.");
        }
    }
    
    */
    Expr objexpr;
    Expr attexpr;
}
    
    