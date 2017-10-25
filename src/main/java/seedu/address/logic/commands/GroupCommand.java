package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * Adds a person to the address book.
 */
public class GroupCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "group";
    public static final String COMMAND_ALIAS = "g";

    public static final String COMMAND_CREATE_WORD = "create";
    public static final String COMMAND_CREATE_ALIAS = "c";

    public static final String COMMAND_DELETE_WORD = "delete";
    public static final String COMMAND_DELETE_ALIAS = "d";

    public static final String COMMAND_INSERT_WORD = "insert";
    public static final String COMMAND_INSERT_ALIAS = "i";

    public static final String COMMAND_REMOVE_WORD = "remove";
    public static final String COMMAND_REMOVE_ALIAS = "r";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add/Delete a group to the address book or Insert/Remove a person from group. \n"
            + "Parameters: \n"
            + "create new group: " + COMMAND_CREATE_WORD + " GROUPNAME \n"
            + "delete group: " + COMMAND_DELETE_WORD + " GROUPNAME \n"
            + "insert user(s) to group: " + COMMAND_INSERT_WORD + " INDEX \n"
            + "remove user(s) to group: " + COMMAND_REMOVE_WORD + " INDEX \n"
            + "Example: \n"
            + COMMAND_WORD + " " + COMMAND_CREATE_WORD + " " + "Savings  \n"
            + COMMAND_WORD + " " + COMMAND_DELETE_WORD + " " + "Savings \n"
            + COMMAND_ALIAS + " " + COMMAND_INSERT_WORD + " " + " 1 \n"
            + COMMAND_ALIAS + " " + COMMAND_REMOVE_WORD + " " + " 1 \n";

    public static final String MESSAGE_SUCCESS_GROUP = "New group added: %1$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in the address book";

    private final GroupName toAdd;

    /**
     * Creates an CreateCommand to add the specified {@code ReadOnlyPerson}
     */
    public GroupCommand(GroupName group) throws IllegalValueException {
        toAdd = new GroupName(group);
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        try {
            model.addPerson(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS_GROUP, toAdd));
        } catch (DuplicatePersonException e) {
            throw new CommandException(MESSAGE_SUCCESS_GROUP);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupCommand // instanceof handles nulls
                && toAdd.equals(((GroupCommand) other).toAdd));
    }
}
