package master.pam.crud.impl.dao.base;

import master.pam.crud.impl.iface.ICrud;

public class BaseDao {

    protected ICrud crud;

    public BaseDao(ICrud crud) {
        this.crud = crud;
    }
}
