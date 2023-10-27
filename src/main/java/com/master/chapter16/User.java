package com.master.chapter16;

import java.util.Objects;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2023-10-27 9:08
 */
public final class User {
    private final String name;
    private final User parent;

    public String getName() {
        return name;
    }

    public User getParent() {
        return parent;
    }

    public User(String name, User user){
        this.name=name;
        this.parent=user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(parent, user.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parent);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", parent=" + parent +
                '}';
    }
}
