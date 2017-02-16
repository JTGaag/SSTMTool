package data.enums;

/**
 * Created by Joost on 16-Feb-17.
 * Enum for port direction
 */
public enum PortDirection {
    IN {
        @Override
        public String toString() {
            return "in";
        }
    },
    OUT {
        @Override
        public String toString() {
            return "out";
        }
    }, INOUT, UNKNOWN
}
