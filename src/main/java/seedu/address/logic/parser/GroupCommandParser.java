package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.GroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.GroupName;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class GroupCommandParser implements Parser<GroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public GroupCommand parse(String args) throws ParseException {

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case GroupCommand.COMMAND_CREATE_WORD:
        case GroupCommand.COMMAND_CREATE_ALIAS:
            System.out.print("HERE");
           return new GroupCommandParser().parseCreate(arguments);

        case GroupCommand.COMMAND_DELETE_WORD:
        case GroupCommand.COMMAND_DELETE_ALIAS:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        case GroupCommand.COMMAND_INSERT_WORD:
        case GroupCommand.COMMAND_INSERT_ALIAS:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        case GroupCommand.COMMAND_REMOVE_WORD:
        case GroupCommand.COMMAND_REMOVE_ALIAS:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    public GroupCommand parseCreate(String args) throws ParseException {
        if(!args.isEmpty()) {
            System.out.println(args);
            try {
                GroupName groupName = ParserUtil.parseGroup(Optional.of(args)).get();

                return new GroupCommand(groupName);
            } catch (IllegalValueException ive) {
                throw new ParseException(ive.getMessage(), ive);
            }
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns null if prefixes contains empty values in the given
     * {@value}.
     */
    private static Optional<String> areValuePresent(Optional<String> value) {
        value = Optional.of(value.orElse(""));
        return value;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
