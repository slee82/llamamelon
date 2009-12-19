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
    public ApostrExpr(Expr objexpr, Identifier attribute) {
        this.objexpr = objexpr;
        this.attribute = attribute;
    }
    
    @Override
    public String code(SymbolTable table) {
    	//Type atttype = attribute.getType(table);
    	if (!table.hasEntry(attribute))
    		throwErr("Identifier: attribute "+ attribute.getID() + " not found in SymbolTable", attribute.getID());
    	
    	if (table.getEntry(attribute) instanceof StatDef) {
    		StatDef attFromTable = (StatDef)table.getEntry(attribute);
    		Type objtype = objexpr.getType(table);
    		Type atttype = attFromTable.getType();
    		String attcode = attribute.getID();
    		String objcode = objexpr.code(table);

    		/* The requested attribute is a teamStat */
    		if (atttype.equals(Type.teamStat)) {
    			if (!objtype.equals(Type.team)) {
    				throwErr("Expr: type mismatch " + objtype + " and " + atttype);
    			}
    			return attFromTable.getName().getID() + ".get(" + objcode + ")";		//TeamObj.statname.get(teamname);
    		}
    		/* The requested attribute is a playerStat */
    		if (atttype.equals(Type.playerStat)) {
    			if (!objtype.equals(Type.player)) {
    				throwErr("objexpr: type mismatch " + objtype + " and " + atttype);
    			}
    			return attFromTable.getName().getID() + ".get(" + objcode + ")";	//PlayerObj.statname.get(playername);
    		}
    		/* The requested attribute is a list */
    		if (atttype.equals(Type.list)) {
    			/* The requested list is of players, batters, or pitchers */
    			if (attcode.equals("PLAYERS") || attcode.equals("PITCHERS") || attcode.equals("BATTERS")) {
    				return attFromTable.getName().getID() + ".get(" + objcode + ")";	//TeamObj.listname.get(teamname);
    			}
    			else
    				throw throwErr("Expr: list " + attcode + " unsuitable for attribute retrieval", attcode);
    		}
    		/* The requested attribute is a string */
    		if (atttype.equals(Type.string)) {
    			if (attcode.equals("name")) {
    				return attFromTable.getName().getID() + ".get(" + objcode + ")";	//PlayerObj.attribute.get(playername);
    			}
    			else
    				throw throwErr("Expr: string " + attcode + " unsuitable for attribute retrieval", attcode);
    		}
        	else 
        		throw throwErr("Expr: type " + atttype + " unsuitable for attribute retrieval", attcode);
    	}
    	else 
    		throw throwErr("Identifier: attribute "+ attribute.getID() + " not a StatDef instance", attribute.getID());
    }
    
    @Override
    public Type getType(SymbolTable table) {
    	if (!table.hasEntry(attribute))
    		throwErr("Identifier: attribute "+ attribute.getID() + " not found in SymbolTable", attribute.getID());
    	if (table.getEntry(attribute) instanceof StatDef) {
    		StatDef attFromTable = (StatDef)table.getEntry(attribute);
    		Type atttype = attFromTable.getType();
    		Type objtype = objexpr.getType(table);

    		if (atttype.equals(Type.teamStat)) {
    			return Type.number;
    		}

    		if (atttype.equals(Type.playerStat)) {
    			return Type.number;
    		}

    		if (atttype.equals(Type.string)) {
    			return Type.string;
    		}
    		
    		if (atttype.equals(Type.list)) {
    			if (attribute.getID().equals("PLAYERS") || attribute.getID().equals("PITCHERS") || attribute.getID().equals("BATTERS")) {
    				return new ListType(Type.player); 
    			}
    			else
    				throwErr("Expr: list " + attribute.getID() + " unsuitable for attribute retrieval", attribute.getID());
    		}

    		if ((atttype.equals(Type.teamStat)) && (!objtype.equals(Type.team))) {
    			throw throwErr("objexpr: type mismatch " + objtype + " and " + atttype);
    		}

    		if ((atttype.equals(Type.playerStat)) && (!objtype.equals(Type.player))) {
    			throw throwErr("objexpr: type mismatch" + objtype + " and " + atttype);
    		}
    		
    		else 
    			throw throwErr("Expr: type " + atttype + " unsuitable for attribute retrieval");
    	}
    	else 
    		throw throwErr("Identifier: attribute "+ attribute.getID() + " not a StatDef instance", attribute.getID());
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
    Identifier attribute;
}
    
    
