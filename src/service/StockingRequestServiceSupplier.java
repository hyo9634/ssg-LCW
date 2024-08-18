package service;

import java.sql.SQLException;
import java.util.ArrayList;
import vo.StockingRequestVO;
import vo.UserVO;

public interface StockingRequestServiceSupplier {

  //입고 요청
  public void requestWarehousing(StockingRequestVO data);

  //내 입고 요청서 조회 -> userid를 필요로 함
  public ArrayList<StockingRequestVO> inquiryWarehouseRequests()
      throws SQLException, InterruptedException;
}
