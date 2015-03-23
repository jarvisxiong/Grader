package commandEngine.tokenizer;

import java.util.Arrays;

import token.*;
import token.command.*;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({ "Scanner Bean" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({ "Line", "Tokens", "Errors" })
@EditablePropertyNames({ "Line" })
public class Tokenizer implements ITokenizer {

	private String line = "";
	private int loc = 0;
	private int errorCount = 0;
	private ClearableTokenHistory tokens = new ClearableTokenHistory();
	private String[] errors = {};

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		loc = 0;
		this.line = line;
		errorCount = 0;
		tokens.clear();
		;
		errors = new String[line.length()];
		buildTokenArray();
		buildErrorArray();
	}

	public ClearableTokenHistory getTokens() {
		return tokens;
	}

	public String[] getErrors() {
		return errors;
	}

	private void buildTokenArray() {
		while (hasNext()) {
			tokens.addToken(next());
		}
		loc = 0;
	}

	private void buildErrorArray() {
		if (errorCount > 0) {
			errors = Arrays.copyOf(errors, errorCount);
		} else {
			errors = new String[] {};
		}
	}

	private boolean isCharacter(char c) {
		return c >= 'A' && c <= 'z';
	}

	private boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	private boolean isQuote(char c) {
		return c == '"';
	}

	private boolean isPlus(char c) {
		return c == '+';
	}

	private boolean isSpecialSingle(char c) {
		return c == '-' || c == '+' || c == '{' || c == '}';
	}

	private boolean isMinus(char c) {
		return c == '-';
	}

	private boolean isStart(char c) {
		return c == '{';
	}

	private boolean isEnd(char c) {
		return c == '}';
	}

	private boolean isWhiteSpace(char c) {
		return c == ' ' || c == '\n' || c == '\t';
	}

	@Override
	public boolean hasNext() {
		return loc < line.length();
	}

	@Override
	public void remove() {
	}

	@Override
	public IToken next() {
		boolean inToken = false;
		TokenType type = null;
		String token = "";

		for (; loc < line.length(); loc++) {
			char cur = line.charAt(loc);

			if (!inToken) {
				if (isQuote(cur)) {
					inToken = true;
					type = TokenType.QUOTED_STRING;
				} else if (isCharacter(cur)) {
					inToken = true;
					type = TokenType.WORD;
					token += cur;
				} else if (isDigit(cur)) {
					inToken = true;
					type = TokenType.NUMBER;
					token += cur;
				} else if (isSpecialSingle(cur)) {
					for (; loc < line.length()
							&& isWhiteSpace(line.charAt(loc)); loc++)
						;
					loc++;

					if (isPlus(cur)) {
						IToken tok = new PlusToken(Character.toString(cur));
						// System.out.println(tok + "\n" + tok.getString());
						return (tok);
					} else if (isMinus(cur)) {
						IToken tok = new MinusToken(Character.toString(cur));
						// System.out.println(tok + "\n" + tok.getString());
						return (tok);
					} else if (isStart(cur)) {
						IToken tok = new StartToken(Character.toString(cur));
						// System.out.println(tok + "\n" + tok.getString());
						return (tok);
					} else if (isEnd(cur)) {
						IToken tok = new EndToken(Character.toString(cur));
						// System.out.println(tok + "\n" + tok.getString());
						return (tok);
					}
				}
			} else {
				if (isQuote(cur)) {
					if (type == TokenType.QUOTED_STRING) {
						loc++;
					}
					return (buildToken(type, token));
				} else if (type == TokenType.QUOTED_STRING) {
					token += cur;
				} else {
					if (isWhiteSpace(cur)) {
						for (; loc < line.length()
								&& isWhiteSpace(line.charAt(loc)); loc++)
							;
						return (buildToken(type, token));
					} else if (isCharacter(cur)) {
						if (type == TokenType.WORD) {
							token += cur;
						} else {
							for (; loc < line.length()
									&& isWhiteSpace(line.charAt(loc)); loc++)
								;
							return (buildToken(type, token));
						}
					} else if (isDigit(cur)) {
						if (type == TokenType.NUMBER) {
							token += cur;
						} else {
							for (; loc < line.length()
									&& isWhiteSpace(line.charAt(loc)); loc++)
								;
							return (buildToken(type, token));
						}
					} else if (isSpecialSingle(cur)) {
						for (; loc < line.length()
								&& isWhiteSpace(line.charAt(loc)); loc++)
							;
						loc++;

						if (isPlus(cur)) {
							IToken tok = new PlusToken(Character.toString(cur));
							// System.out.println(tok + "\n" + tok.getString());
							return (tok);
						} else if (isMinus(cur)) {
							IToken tok = new MinusToken(Character.toString(cur));
							// System.out.println(tok + "\n" + tok.getString());
							return (tok);
						} else if (isStart(cur)) {
							IToken tok = new StartToken(Character.toString(cur));
							// System.out.println(tok + "\n" + tok.getString());
							return (tok);
						} else if (isEnd(cur)) {
							IToken tok = new EndToken(Character.toString(cur));
							// System.out.println(tok + "\n" + tok.getString());
							return (tok);
						}
					}
				}
			}
		}

		if (inToken) {
			if (type != TokenType.QUOTED_STRING) {
				return (buildToken(type, token));
			} else {
				errors[errorCount] = "Missing end quote!";
				errorCount++;
				return (buildToken(type, token));
			}
		}

		return null;
	}

	private IToken buildToken(TokenType type, String token) {

		switch (type) {
		case NUMBER:
			INumberToken numTok = new NumberToken(token);
			// System.out.println(numTok + "\n" + numTok.getString() + "\n" +
			// numTok.getNumber());
			return numTok;
		case WORD:
			IWordToken wordTok = checkWordToken(token);
			// System.out.println(wordTok + "\n" + wordTok.getString() + "\n" +
			// wordTok.getWord());
			return wordTok;
		case QUOTED_STRING:
			IQuotedStringToken quotTok = new QuotedStringToken(token);
			// System.out.println(quotTok + "\n" + quotTok.getString());
			return quotTok;
		default:
			return null;
		}
	}

	private IWordToken checkWordToken(String token) {
		switch (token.toLowerCase()) {
		case "approach":
			return new ApproachCommandToken(token);
		case "call":
			return new CallCommandToken(token);
		case "define":
			return new DefineCommandToken(token);
		case "fail":
			return new FailCommandToken(token);
		case "move":
			return new MoveCommandToken(token);
		case "undo":
			return new UndoCommandToken(token);
		case "pass":
			return new PassCommandToken(token);
		case "proceedall":
			return new ProceedAllCommandToken(token);
		case "redo":
			return new RedoCommandToken(token);
		case "repeat":
			return new RepeatCommandToken(token);
		case "rotateleftarm":
			return new RotateLeftArmCommandToken(token);
		case "rotaterightarm":
			return new RotateRightArmCommandToken(token);
		case "say":
			return new SayCommandToken(token);
		case "sleep":
			return new SleepCommandToken(token);
		case "thread":
			return new ThreadCommandToken(token);
		case "wait":
			return new WaitCommandToken(token);
		default:
			return new WordToken(token);
		}
	}
}
