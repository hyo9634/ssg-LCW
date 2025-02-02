package common.dao;


import static util.Role.STORE_OPERATOR;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import lib.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;
import vo.StockVO;
import vo.UserVO;

public class StocktakingDAO {

  private ConnectionPool conncp = null;
  private Connection connection = null;
  private ResultSet rs = null;
  private String sql;
  private PreparedStatement pstmt;
  StockVO stock = null;
  CallableStatement cstmt = null;

  //  public StocktakingDAO(UserVO userVO){
//    init();
//    this.userVO = userVO;
//  }
  public StocktakingDAO(){
    init();
    //this.userVO = userVO;
  }

  private void init(){
    conncp = ConnectionPool.getInstance();
  }

  //대분류 재고 목록 출력
  public List<StockVO> majorStockList(UserVO userVO,String categoryName) throws SQLException, InterruptedException {

    List<StockVO> stockList = new ArrayList<StockVO>();
    this.connection = conncp.getConnection(100);
    try {
      sql = " SELECT "
          + "    s.user_id, "
          + "    s.storage_id, "
          + "    st.storage_name, "
          + "    s.product_id, "
          + "    pd.product_Name, "
          + "    ma.major_category_name, "
          + "    mi.middle_category_name, "
          + "    sm1.small_category_name, "
          + "    s.storage_quantity "
          + " FROM stock s  "
          + " JOIN product pd "
          + "    ON s.product_Id = pd.product_Id  "
          + " JOIN storage st "
          + "    ON s.storage_id = st.storage_id "
          + " JOIN small_category sm1 "
          + "    ON pd.small_category_id = sm1.small_category_id "
          + " JOIN middle_category mi "
          + "    ON sm1.middle_category_id = mi.middle_category_id "
          + " JOIN major_category ma "
          + "    ON mi.major_category_id = ma.major_category_id "
          + " WHERE ma.major_category_name = ?";

      if(userVO.getRole().equals(STORE_OPERATOR))sql+= " AND s.user_id = "+userVO.getUserId();

      pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, categoryName);
      rs = pstmt.executeQuery();

      conncp.releaseConnection(this.connection);

      while(rs.next()) {

        stock = new StockVO();
        stock.setUserId(rs.getString(1));
        stock.setStorageId(rs.getInt(2));
        stock.setStorageName(rs.getString(3));
        stock.setProductId(rs.getInt(4));
        stock.setProductName(rs.getString(5));
        stock.setMajorCategoryName(rs.getString(6));
        stock.setMiddleCategoryName(rs.getString(7));
        stock.setSmallCategoryName(rs.getString(8));
        stock.setQuantity(rs.getInt(9));

        stockList.add(stock);
      }
      rs.close();
      pstmt.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return stockList;
  }

  //중분류 재고 목록 출력
  public List<StockVO> middleStockList(UserVO userVO, String categoryName) throws SQLException, InterruptedException{

    List<StockVO> stockList = new ArrayList<StockVO>();
    this.connection = conncp.getConnection(100);

    try {
      sql = " SELECT "
          + "    s.user_id, "
          + "    s.storage_id, "
          + "    st.storage_name, "
          + "    s.product_id, "
          + "    pd.product_Name, "
          + "    ma.major_category_name, "
          + "    mi.middle_category_name, "
          + "    sm1.small_category_name, "
          + "    s.storage_quantity "
          + " FROM stock s  "
          + " JOIN product pd "
          + "    ON s.product_Id = pd.product_Id  "
          + " JOIN storage st "
          + "    ON s.storage_id = st.storage_id "
          + " JOIN small_category sm1 "
          + "    ON pd.small_category_id = sm1.small_category_id "
          + " JOIN middle_category mi "
          + "    ON sm1.middle_category_id = mi.middle_category_id "
          + " JOIN major_category ma "
          + "    ON mi.major_category_id = ma.major_category_id "
          + " WHERE mi.middle_category_name = ?";

      if(userVO.getRole().equals(STORE_OPERATOR))sql+= " AND s.user_id = "+userVO.getUserId();

      pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, categoryName);
      rs = pstmt.executeQuery();

      conncp.releaseConnection(this.connection);

      while(rs.next()) {

        stock = new StockVO();
        stock.setUserId(rs.getString(1));
        stock.setStorageId(rs.getInt(2));
        stock.setStorageName(rs.getString(3));
        stock.setProductId(rs.getInt(4));
        stock.setProductName(rs.getString(5));
        stock.setMajorCategoryName(rs.getString(6));
        stock.setMiddleCategoryName(rs.getString(7));
        stock.setSmallCategoryName(rs.getString(8));
        stock.setQuantity(rs.getInt(9));

        stockList.add(stock);
      }
      rs.close();
      pstmt.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return stockList;
  }


  //소분류 재고 목록 출력
  public List<StockVO> smallStockList(UserVO userVO,String categoryName) throws SQLException, InterruptedException{

    List<StockVO> stockList = new ArrayList<StockVO>();
    this.connection = conncp.getConnection(100);
    try {
      sql = " SELECT "
          + "    s.user_id, "
          + "    s.storage_id, "
          + "    st.storage_name, "
          + "    s.product_id, "
          + "    pd.product_Name, "
          + "    ma.major_category_name, "
          + "    mi.middle_category_name, "
          + "    sm1.small_category_name, "
          + "    s.storage_quantity "
          + " FROM stock s  "
          + " JOIN product pd "
          + "    ON s.product_Id = pd.product_Id  "
          + " JOIN storage st "
          + "    ON s.storage_id = st.storage_id "
          + " JOIN small_category sm1 "
          + "    ON pd.small_category_id = sm1.small_category_id "
          + " JOIN middle_category mi "
          + "    ON sm1.middle_category_id = mi.middle_category_id "
          + " JOIN major_category ma "
          + "    ON mi.major_category_id = ma.major_category_id "
          + " WHERE pd.small_category_name = ?";

      if(userVO.getRole().equals(STORE_OPERATOR))sql+= " AND s.user_id = "+userVO.getUserId();

      pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, categoryName);
      rs = pstmt.executeQuery();

      conncp.releaseConnection(this.connection);

      while(rs.next()) {

        stock = new StockVO();
        stock.setUserId(rs.getString(1));
        stock.setStorageId(rs.getInt(2));
        stock.setStorageName(rs.getString(3));
        stock.setProductId(rs.getInt(4));
        stock.setProductName(rs.getString(5));
        stock.setMajorCategoryName(rs.getString(6));
        stock.setMiddleCategoryName(rs.getString(7));
        stock.setSmallCategoryName(rs.getString(8));
        stock.setQuantity(rs.getInt(9));

        stockList.add(stock);
      }
      rs.close();
      pstmt.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return stockList;
  }

  //전체 재고 목록 출력
  public List<StockVO> wholeStockList(UserVO userVO) throws SQLException, InterruptedException{

    List<StockVO> stockList = new ArrayList<StockVO>();
    this.connection = conncp.getConnection(100);
    try {
      sql = " SELECT "
          + "    s.user_id, "
          + "    s.storage_id, "
          + "    st.storage_name, "
          + "    s.product_id, "
          + "    pd.product_Name, "
          + "    ma.major_category_name, "
          + "    mi.middle_category_name, "
          + "    sm1.small_category_name, "
          + "    s.storage_quantity "
          + " FROM stock s  "
          + " JOIN product pd "
          + "    ON s.product_Id = pd.product_Id  "
          + " JOIN storage st "
          + "    ON s.storage_id = st.storage_id "
          + " JOIN small_category sm1 "
          + "    ON pd.small_category_id = sm1.small_category_id "
          + " JOIN middle_category mi "
          + "    ON sm1.middle_category_id = mi.middle_category_id "
          + " JOIN major_category ma "
          + "    ON mi.major_category_id = ma.major_category_id ";

      if(userVO.getRole().equals(STORE_OPERATOR))sql+= " where s.user_id = "+userVO.getUserId();

      pstmt = connection.prepareStatement(sql);
      rs = pstmt.executeQuery();

      conncp.releaseConnection(this.connection);

      while(rs.next()) {

        stock = new StockVO();
        stock.setUserId(rs.getString(1));
        stock.setStorageId(rs.getInt(2));
        stock.setStorageName(rs.getString(3));
        stock.setProductId(rs.getInt(4));
        stock.setProductName(rs.getString(5));
        stock.setMajorCategoryName(rs.getString(6));
        stock.setMiddleCategoryName(rs.getString(7));
        stock.setSmallCategoryName(rs.getString(8));
        stock.setQuantity(rs.getInt(9));

        stockList.add(stock);
      }
      rs.close();
      pstmt.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return stockList;

  }

  //상품명 재고 목록 출력
  public List<StockVO> keywordStockList(UserVO userVO,String input) throws SQLException, InterruptedException{

    List<StockVO> stockList = new ArrayList<StockVO>();
    this.connection = conncp.getConnection(100);
    try {
      sql = " SELECT "
          + "    s.user_id, "
          + "    s.storage_id, "
          + "    st.storage_name, "
          + "    s.product_id, "
          + "    pd.product_Name, "
          + "    ma.major_category_name, "
          + "    mi.middle_category_name, "
          + "    sm1.small_category_name, "
          + "    s.storage_quantity "
          + " FROM stock s  "
          + " JOIN product pd "
          + "    ON s.product_Id = pd.product_Id  "
          + " JOIN storage st "
          + "    ON s.storage_id = st.storage_id "
          + " JOIN small_category sm1 "
          + "    ON pd.small_category_id = sm1.small_category_id "
          + " JOIN middle_category mi "
          + "    ON sm1.middle_category_id = mi.middle_category_id "
          + " JOIN major_category ma "
          + "    ON mi.major_category_id = ma.major_category_id "
          + " where s.product_Id = %?% OR pd.product_Name like =%?%";

      if(userVO.getRole().equals(STORE_OPERATOR))sql+= " and s.user_id = "+userVO.getUserId();

      pstmt = connection.prepareStatement(sql);
      pstmt.setInt(1, Integer.valueOf(input));
      pstmt.setString(2, input);
      rs = pstmt.executeQuery();

      conncp.releaseConnection(this.connection);

      while(rs.next()) {

        stock = new StockVO();
        stock.setUserId(rs.getString(1));
        stock.setStorageId(rs.getInt(2));
        stock.setStorageName(rs.getString(3));
        stock.setProductId(rs.getInt(4));
        stock.setProductName(rs.getString(5));
        stock.setMajorCategoryName(rs.getString(6));
        stock.setMiddleCategoryName(rs.getString(7));
        stock.setSmallCategoryName(rs.getString(8));
        stock.setQuantity(rs.getInt(9));

        stockList.add(stock);
      }
      rs.close();
      pstmt.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stockList;
  }

  //검색어로 재고 목록 출력
  public List<StockVO> userdStockList(String userId) throws SQLException, InterruptedException{

    List<StockVO> stockList = new ArrayList<StockVO>();
    this.connection = conncp.getConnection(100);
    try {
      sql = " SELECT "
          + "    s.user_id, "
          + "    s.storage_id, "
          + "    st.storage_name, "
          + "    s.product_id, "
          + "    pd.product_Name, "
          + "    ma.major_category_name, "
          + "    mi.middle_category_name, "
          + "    sm1.small_category_name, "
          + "    s.storage_quantity "
          + " FROM stock s  "
          + " JOIN product pd "
          + "    ON s.product_Id = pd.product_Id  "
          + " JOIN storage st "
          + "    ON s.storage_id = st.storage_id "
          + " JOIN small_category sm1 "
          + "    ON pd.small_category_id = sm1.small_category_id "
          + " JOIN middle_category mi "
          + "    ON sm1.middle_category_id = mi.middle_category_id "
          + " JOIN major_category ma "
          + "    ON mi.major_category_id = ma.major_category_id "
          + " where s.user_Id = ?";


      pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, userId);
      rs = pstmt.executeQuery();

      conncp.releaseConnection(this.connection);

      while(rs.next()) {

        stock = new StockVO();
        stock.setUserId(rs.getString(1));
        stock.setStorageId(rs.getInt(2));
        stock.setStorageName(rs.getString(3));
        stock.setProductId(rs.getInt(4));
        stock.setProductName(rs.getString(5));
        stock.setMajorCategoryName(rs.getString(6));
        stock.setMiddleCategoryName(rs.getString(7));
        stock.setSmallCategoryName(rs.getString(8));
        stock.setQuantity(rs.getInt(9));

        stockList.add(stock);
      }
      rs.close();
      pstmt.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stockList;
  }

  //검색어+창고번호로 재고 목록 출력
  public List<StockVO> keywordAndStorageIdStockList(UserVO userVO,int input, int storageNum) throws SQLException, InterruptedException{

    List<StockVO> stockList = new ArrayList<StockVO>();
    this.connection = conncp.getConnection(100);
    try {
      sql = " SELECT "
          + "    s.user_id, "
          + "    s.storage_id, "
          + "    st.storage_name, "
          + "    s.product_id, "
          + "    pd.product_Name, "
          + "    ma.major_category_name, "
          + "    mi.middle_category_name, "
          + "    sm1.small_category_name, "
          + "    s.storage_quantity "
          + " FROM stock s  "
          + " JOIN product pd "
          + "    ON s.product_Id = pd.product_Id  "
          + " JOIN storage st "
          + "    ON s.storage_id = st.storage_id "
          + " JOIN small_category sm1 "
          + "    ON pd.small_category_id = sm1.small_category_id "
          + " JOIN middle_category mi "
          + "    ON sm1.middle_category_id = mi.middle_category_id "
          + " JOIN major_category ma "
          + "    ON mi.major_category_id = ma.major_category_id "
          + " where s.product_id = ? "
          + " and s.storage_id = ?";

      if(userVO.getRole().equals(STORE_OPERATOR))sql+= " AND s.user_id = "+userVO.getUserId();

      pstmt = connection.prepareStatement(sql);
      pstmt.setInt(1, input);
      pstmt.setInt(2, storageNum);
      rs = pstmt.executeQuery();

      conncp.releaseConnection(this.connection);

      while(rs.next()) {

        stock = new StockVO();
        stock.setUserId(rs.getString(1));
        stock.setStorageId(rs.getInt(2));
        stock.setStorageName(rs.getString(3));
        stock.setProductId(rs.getInt(4));
        stock.setProductName(rs.getString(5));
        stock.setMajorCategoryName(rs.getString(6));
        stock.setMiddleCategoryName(rs.getString(7));
        stock.setSmallCategoryName(rs.getString(8));
        stock.setQuantity(rs.getInt(9));

        stockList.add(stock);
      }
      rs.close();
      pstmt.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return stockList;
  }


  //창고 현황 조회
  public List<StockVO> displayStorageStatus()  throws SQLException, InterruptedException{

    List<StockVO> stockList = new ArrayList<StockVO>();
    this.connection = conncp.getConnection(100);
    try {
      sql = " select s.storage_id as 창고번호, "
          + " st.storage_name as 창고이름 , "
          + " storage_area as 창고면적, "
          + " sum(s.storage_quantity)*area_per_product as 재고량 "
          + " , round((sum(s.storage_quantity)*area_per_product / storage_area)*100,1) as '창고사용량(%)' "
          + " from stock s "
          + " JOIN product pd ON s.product_Id = pd.product_Id "
          + " JOIN storage st ON s.storage_id = st.storage_id "
          + " group by s.product_id, s.storage_id";

      pstmt = connection.prepareStatement(sql);

      rs = pstmt.executeQuery();

      conncp.releaseConnection(this.connection);

      while(rs.next()) {
        stock = new StockVO();
        stock.setStorageId(rs.getInt(1));
        stock.setStorageName(rs.getString(2));
        stock.setStorageArea(rs.getInt(3));
        stock.setStorageUsage(rs.getInt(4));
        stock.setStorageUsageRate(Integer.toString(rs.getInt(5))+"%");
        stockList.add(stock);
      }
      rs.close();
      pstmt.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stockList;
  }

  //거래처 현황 조회 displayClientStorageUsage
  public List<StockVO> displayClientStorageUsage()  throws SQLException, InterruptedException{

    List<StockVO> stockList = new ArrayList<StockVO>();
    this.connection = conncp.getConnection(100);
    try {
      sql = "  select "
          + " s.user_id as 고객번호, "
          + " c.contract_area as 계약면적 , "
          + "    a.b as 사용면적, "
          + "    c.contract_area-a.b as '사용 가능 면적', "
          + "    round((a.b / contract_area)*100,1) as '창고 사용량(%)' "
          + "from stock s "
          + " JOIN contract c ON s.user_id = c.user_id "
          + " JOIN ( "
          + " select  user_id, sum(s.storage_quantity*area_per_product) as 'b' "
          + " from stock s join product p on s.product_id = p.product_id "
          + " group by user_id) a ON a.user_id = s.user_id "
          + "group by s.user_id";

      pstmt = connection.prepareStatement(sql);

      rs = pstmt.executeQuery();

      conncp.releaseConnection(this.connection);

      while(rs.next()) {
        stock = new StockVO();
        stock.setUserId(rs.getString(1));
        stock.setStorageArea(rs.getInt(2));
        stock.setStorageUsage(rs.getInt(3));
        stock.setUsableStorageArea(rs.getInt(4));
        stock.setStorageUsageRate(Integer.toString(rs.getInt(5))+"%");
        stockList.add(stock);
      }
      rs.close();
      pstmt.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return stockList;
  }

  //재고 실사 수정
  public void editStockCount(UserVO userVO,int productId, int storageId, int quantity){
    this.connection = conncp.getConnection(100);

    try{
      sql = " update stock "
          + " set storage_quantity = ? "
          + " where "
          + " product_id = ? "
          + " and storage_id = ?";
      pstmt = connection.prepareStatement(sql);

      pstmt.setInt(1,quantity);
      pstmt.setInt(2,productId);
      pstmt.setInt(3, storageId);
      pstmt.executeUpdate();
      conncp.releaseConnection(this.connection);
      System.out.println("재고 수량이 업데이트 되었습니다.");
      keywordAndStorageIdStockList(userVO,productId,storageId);
      pstmt.close();

    }catch (Exception e){
      e.printStackTrace();
    }

  }

  //재고 삭제 수정 -> 어떤 상황에 삭제할건지? 계약 만료 or 더 이상 취급하지 않는 제품(product에서 삭제?)
  public void deleteStockCount(UserVO userVO,int productId, int storageNum){
    this.connection = conncp.getConnection(100);
    Scanner sc = new Scanner(System.in);

    try{
      sql = " delete from stock "
          + " where "
          + " product_id = ? "
          + " and storage_id = ?";
      pstmt = connection.prepareStatement(sql);
      pstmt.setInt(1,productId);
      pstmt.setInt(2,storageNum);
      int result = pstmt.executeUpdate(sql);
      if (result == 1) {
        System.out.println("재고가 성공적으로 삭제되었습니다.");
      }
      conncp.releaseConnection(this.connection);

      wholeStockList(userVO);
      pstmt.close();

    }catch (Exception e){
      e.printStackTrace();
    }

  }

  //재고 업로드
  public void uploadStockCount(String userID, int storageId, int productId, int quantity){
    this.connection = conncp.getConnection(100);

    try{
      sql = " INSERT INTO stock "
          + " VALUES( "
          + " ?, ?, ?, ? )";
      pstmt = connection.prepareStatement(sql);

      pstmt.setString(1,userID);
      pstmt.setInt(2,storageId);
      pstmt.setInt(3,productId);
      pstmt.setInt(4,quantity);
      int result = pstmt.executeUpdate();
      if (result == 1) {
        System.out.println("재고가 업로드 되었습니다.");
      } else {
        System.out.println("재고가 등록되지 않았습니다.");
      }
      conncp.releaseConnection(this.connection);

      pstmt.close();

    }catch (Exception e){
      e.printStackTrace();
    }

  }





}
