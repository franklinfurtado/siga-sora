package co.ao.sigp.catequese.core.util.mysqldialect;

import java.util.List;

import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.Type;

public class CustomStandardSQLFunction implements SQLFunction {
	private final String name;
	private final Type registeredType;

	/**
	 * Construct a standard SQL function definition with a variable return type;
	 * the actual return type will depend on the types to which the function
	 * is applied.
	 * <p/>
	 * Using this form, the return type is considered non-static and assumed
	 * to be the type of the first argument.
	 *
	 * @param name The name of the function.
	 */
	public CustomStandardSQLFunction(String name) {
		this( name, null );
	}

	/**
	 * Construct a standard SQL function definition with a static return type.
	 *
	 * @param name The name of the function.
	 * @param registeredType The static return type.
	 */
	public CustomStandardSQLFunction(String name, Type registeredType) {
		this.name = name;
		this.registeredType = registeredType;
	}

	/**
	 * Function name accessor
	 *
	 * @return The function name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Function static return type accessor.
	 *
	 * @return The static function return type; or null if return type is
	 * not static.
	 */
	public Type getType() {
		return registeredType;
	}

	@Override
	public boolean hasArguments() {
		return true;
	}

	@Override
	public boolean hasParenthesesIfNoArguments() {
		return true;
	}

	@Override
	public Type getReturnType(Type firstArgumentType, Mapping mapping) {
		return registeredType == null ? firstArgumentType : registeredType;
	}

	@Override
	public String render(Type firstArgumentType, @SuppressWarnings("rawtypes") List arguments, SessionFactoryImplementor sessionFactory) {
		final StringBuilder buf = new StringBuilder();
		buf.append( getRenderedName( arguments) ).append( "(" );
		for ( int i = 0; i < arguments.size(); i++ ) {
			buf.append( arguments.get( i ) );
			if ( i < arguments.size() - 1 ) {
				buf.append( ", " );
			}
		}
		return buf.append( " SEPARATOR ', ')" ).toString();
	}

	protected String getRenderedName(List<?> arguments) {
		return getName();
	}

	@Override
	public String toString() {
		return name;
	}
}
