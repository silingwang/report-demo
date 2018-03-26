package org.spring.springboot.entity;

import lombok.*;

import java.io.*;

/**
 * Created by nice on 2018/3/20.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable ,Cloneable{

    public Long id;
    public String userName;
    public Integer age;

    /**
     * 使用序列化进行 深度克隆 也名字深拷贝
     * @return
     */
    public User cloneDdeep() {
        User outer = null;
        try { // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            // 将流序列化成对象
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            outer = (User) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return outer;
    }

    /**
     * 浅克隆
     * @return
     */
    @Override
    public User clone()  {
        User clone = null;
        try {
            clone = (User) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (!id.equals(user.id)) return false;
        if (!userName.equals(user.userName)) return false;
        return age.equals(user.age);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + age.hashCode();
        return result;
    }


}
