package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGroupName(String)}
 */
public class GroupName implements Comparable<GroupName>, Comparator<GroupName> {

    public static final String MESSAGE_GROUP_CONSTRAINTS =
            "Person names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String GROUP_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String groupName;

    /**
     * Validates group name.
     *
     * @throws IllegalValueException if group name string is invalid.
     */
    public GroupName(String groupName) throws IllegalValueException {
        requireNonNull(groupName);
        String trimmedGroupName = groupName.trim();
        if (!isValidGroupName(trimmedGroupName)) {
            throw new IllegalValueException(MESSAGE_GROUP_CONSTRAINTS);
        }
        this.groupName = trimmedGroupName;
    }

    /**
     * Returns true if a given string is a valid group name.
     */
    public static boolean isValidGroupName(String test) {
        return test.matches(GROUP_VALIDATION_REGEX);
    }

    public int compareTo(GroupName n) {
        return this.groupName.compareTo(n.groupName);
    }

    public int compare(GroupName a, GroupName b) {
        return a.groupName.compareTo(b.groupName);
    }

    @Override
    public String toString() {
        return groupName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupName // instanceof handles nulls
                && this.groupName.equals(((GroupName) other).groupName)); // state check
    }

    @Override
    public int hashCode() {
        return groupName.hashCode();
    }


}
