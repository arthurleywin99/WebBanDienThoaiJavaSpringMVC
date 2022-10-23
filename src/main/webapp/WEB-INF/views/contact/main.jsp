<%-- 
    Document   : main.jsp
    Created on : Oct 15, 2022, 4:27:42 PM
    Author     : buing
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <div class="row">
        <div class="col-md-5">
            <form name="sendcontact" action="sendcontact" method="POST">
                <h4 class="font-weight-bold border-left border-warning pl-md-2 text-uppercase" style="border-left-width: 5px !important;">Apple Store xin hân hạnh hỗ trợ quý khách</h4>
                <c:if test="${Success == 'Success'}">
                    <div class="p-3 mb-2 bg-success text-white">Gửi liên hệ thành công</div>
                </c:if>
                <c:if test="${Failure == 'Failure'}">
                    <div class="p-3 mb-2 bg-danger text-white">Có lỗi xảy ra, vui lòng thử lại</div>
                </c:if>
                <div class="rounded border border-secondary p-md-2">
                    <div class="row ml-md-2">
                        <div class="col-md-6 text-left d-md-flex align-items-center">Quý khách đang quan tâm:</div>
                        <div class="col-md-6">
                            <select class="custom-select" id="inputGroupSelect01" name="IDProblem">
                                <c:forEach items="${problemContacts}" var="item">
                                    <option value="${item.getID()}">${item.getContent()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row ml-md-2">
                        <div class="col-md-4 text-left d-md-flex align-items-center pt-md-0 pb-md-3">Tiêu đề:</div>
                        <div class="col-md-8 pt-md-0 pb-md-3">
                            <input class="form-control" type="text" name="Title" required />
                        </div>
                    </div>
                    <div class="row ml-md-2">
                        <div class="col-md-4 text-left d-md-flex align-items-baseline pt-md-0 pb-md-3">Nội dung:</div>
                        <div class="col-md-8 pt-md-0 pb-md-3">
                            <textarea class="form-control" style="resize: none;" rows="4" name="Content" required></textarea>
                        </div>
                    </div>
                    <div class="row ml-md-2">
                        <div class="col-md-4 text-left d-md-flex align-items-center pt-md-0 pb-md-3">Họ Tên:</div>
                        <div class="col-md-8 pt-md-0 pb-md-3">
                            <input class="form-control" type="text" name="Fullname" required />
                        </div>
                    </div>
                    <div class="row ml-md-2">
                        <div class="col-md-4 text-left d-md-flex align-items-center pt-md-0 pb-md-3">Địa chỉ email:</div>
                        <div class="col-md-8 pt-md-0 pb-md-3">
                            <input class="form-control" id="email" type="email" name="Email" required />
                        </div>
                    </div>
                    <div class="row ml-md-2">
                        <div class="col-md-4 text-left d-md-flex align-items-center pt-md-0 pb-md-3">Số điện thoại:</div>
                        <div class="col-md-8 pt-md-0 pb-md-3">
                            <input class="form-control" type="text" name="PhoneNumber" minlength="10" maxlength="13" required />
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary btn-lg btn-block mt-md-3 text-uppercase">Gửi liên hệ</button>
            </form>
        </div>
        <div class="col-7">
            <h4 class="font-weight-bold border-left border-warning pl-md-2 text-uppercase" style="border-left-width: 5px !important;">Thông tin liên hệ khác</h4>
            <hr class="mr-md-4" />
            <div class="d-md-flex">
                <h5>Hợp tác, liên hệ:&nbsp;</h5>
                <a class="text-decoration-none" href="mailto:${HREmail}"><h5 class="text-primary">phongnhansu@applestore.com</h5></a>
            </div>
            <div class="d-md-flex">
                <h5>Tổng đài tư vấn, hỗ trợ khách hàng (7h30 đến 21h):&nbsp;</h5>
                <a class="text-decoration-none" href="tel:${PhoneContact}"><h5 class="text-warning font-weight-bold">0354.321.915</h5></a>
            </div>
            <div class="d-md-flex">
                <h5>Tổng đài khiếu nại:&nbsp;</h5>
                <a class="text-decoration-none" href="tel:${PhoneContact}"><h5 class="text-warning font-weight-bold">0354.321.915</h5></a>
            </div>
            <div class="d-md-flex">
                <h5>Ẹmail:&nbsp;</h5>
                <a class="text-decoration-none" href="mailto:${CCEmail}"><h5 class="text-primary">cskh@applestore.com</h5></a>
            </div>
            <div>
                <iframe src="${GoogleMapLocation}" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
        </div>
    </div>
</div>