package master.pam.crud.api.dao;

public interface IPasswordDao {

    public void insert(String aPassword, Long aUserId);

    public void update(String aPassword, Long aUserId);

    public boolean isCorrect(String aPassword, Long aUserId);

}
