<%-- 
    Document   : main
    Created on : Oct 20, 2022, 9:14:31 AM
    Author     : buing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2>Dashboard</h2>

<div class="container-fluid bg-gray-light">
    <div class="row mb-md-5">
        <div class="col-md-3">
            <div class="bg-info rounded w-100 p-md-3">
                <div class="row">
                    <div class="col-md-6">
                        <h3 id="order-count" class="font-weight-bold">NaN</h3>
                        <h5>Tổng đơn hàng</h5>
                    </div>
                    <div class="col-md-6" align="right">
                        <i class="fal fa-shopping-bag" style="font-size: 70px; color: rgba(0,0,0,.15);"></i>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="bg-success rounded w-100 p-md-3">
                <div class="row">
                    <div class="col-md-6">
                        <h3 id="increase-rate" class="font-weight-bold">NaN %</h3>
                        <h5>Tỉ lệ tăng</h5>
                    </div>
                    <div class="col-md-6" align="right">
                        <i class="fal fa-chart-bar" style="font-size: 70px; color: rgba(0,0,0,.15);"></i>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="bg-warning rounded w-100 p-md-3">
                <div class="row">
                    <div class="col-md-6">
                        <h3 id="register-count" class="font-weight-bold" style="color: #fffffb;">NaN</h3>
                        <h5 style="color: #fffffb;">Số người đăng ký</h5>
                    </div>
                    <div class="col-md-6" align="right">
                        <i class="fal fa-user-plus" style="font-size: 70px; color: rgba(0,0,0,.15);"></i>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="bg-danger rounded w-100 p-md-3">
                <div class="row">
                    <div class="col-md-6">
                        <h3 id="statistic-sum" class="font-weight-bold">NaN</h3>
                        <h5>Doanh thu</h5>
                    </div>
                    <div class="col-md-6" align="right">
                        <i class="fal fa-chart-pie" style="font-size: 70px; color: rgba(0,0,0,.15);"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="btn-group">
        <button id="condition" type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Chọn tiêu chí...</button>
        <div class="dropdown-menu">
            <a id="today" class="dropdown-item" href="#">Hôm nay</a>
            <a id="month" class="dropdown-item" href="#">Tháng này</a>
            <a id="year" class="dropdown-item" href="#">Năm này</a>
            <a id="all" class="dropdown-item" href="#">Từ trước đến nay</a>
        </div>
    </div>

    <div class="row">
        <div class="col-md-7 rounded ml-md-2 mr-md-5" style="background-color: #fff;">
            <div id="chartContainer1">
                <canvas id="chartProduct1"></canvas>
            </div>
        </div>
        <div class="col-md-4 rounded ml-md-4" style="background-color: #fff;">
            <div id="chartContainer2">
                <canvas id="chartProduct2"></canvas>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(() => {
        const bgColor = [
            'rgba(255, 99, 132, 0.2)',
            'rgba(255, 159, 64, 0.2)',
            'rgba(255, 205, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(153, 102, 255, 0.2)',
            'rgba(201, 203, 207, 0.2)',
            'rgb(255, 0, 0, 0.2)',
            'rgb(238, 130, 238, 0.2)',
            'rgb(255, 165, 0, 0.2)',
            'rgb(180, 180, 180, 0.2)',
            'rgba(255, 99, 71, 0.2)'
        ];

        const borColor = [
            'rgb(255, 99, 132)',
            'rgb(255, 159, 64)',
            'rgb(255, 205, 86)',
            'rgb(75, 192, 192)',
            'rgb(54, 162, 235)',
            'rgb(153, 102, 255)',
            'rgb(201, 203, 207)',
            'rgb(255, 0, 0)',
            'rgb(238, 130, 238)',
            'rgb(255, 165, 0)',
            'rgb(180, 180, 180)',
            'rgba(255, 99, 71)'
        ];

        $("#today").click((e) => {
            let button = $(e.target);
            let parentButton = $("#condition");
            parentButton.text(button.text());

            axios.get('<c:url value="/api/statistic/today" />').then(response => {
                const res = response.data;
                if (res.message === "success") {
                    const orderCount = res.orderCount;
                    const increaseRate = res.increaseRate;
                    const registerCount = res.registerCount;
                    const statisticSum = res.statisticSum;
                    const labels1 = [];
                    const values1 = [];
                    res.jsonCountData.map(item => {
                        labels1.push(item.key);
                        values1.push(item.value);
                    });

                    const data1 = {
                        labels: labels1,
                        datasets: [{
                                label: "Thống kê số lượng đơn hàng hôm nay",
                                data: values1,
                                backgroundColor: bgColor,
                                borderColor: borColor,
                                borderWidth: 1
                            }]
                    };
                    const config1 = {
                        type: 'bar',
                        data: data1,
                        options: {
                            scales: {
                                y: {
                                    beginAtZero: true
                                }
                            }
                        },
                    };

                    const labels2 = [];
                    const values2 = [];
                    res.jsonIncomeData.map(item => {
                        labels2.push(item.key);
                        values2.push(item.value);
                    });

                    const data2 = {
                        labels: labels2,
                        datasets: [{
                                label: "Thống kê số tiền hôm nay",
                                data: values2,
                                backgroundColor: bgColor,
                                borderColor: borColor,
                                borderWidth: 1
                            }]
                    };

                    const config2 = {
                        type: 'doughnut',
                        data: data2,
                    };

                    $("#order-count").text(orderCount);
                    $("#increase-rate").text(increaseRate + '%');
                    $("#register-count").text(registerCount);
                    $("#statistic-sum").text(statisticSum);

                    $("#chartProduct1").remove();
                    $("#chartProduct2").remove();

                    $("#chartContainer1").append('<canvas id="chartProduct1"></canvas>');
                    $("#chartContainer2").append('<canvas id="chartProduct2"></canvas>');

                    const myBarChart1 = new Chart(document.getElementById('chartProduct1'), config1);
                    const myBarChart2 = new Chart(document.getElementById('chartProduct2'), config2);
                } else {
                    alert("Có lỗi xảy ra");
                }
            }).catch(error => console.log(error)).finally();
        });

        $("#month").click((e) => {
            let button = $(e.target);
            let parentButton = $("#condition");
            parentButton.text(button.text());

            axios.get('<c:url value="/api/statistic/month" />').then(response => {
                const res = response.data;
                if (res.message === "success") {
                    const orderCount = res.orderCount;
                    const increaseRate = res.increaseRate;
                    const registerCount = res.registerCount;
                    const statisticSum = res.statisticSum;
                    const labels1 = [];
                    const values1 = [];
                    res.jsonCountData.map(item => {
                        labels1.push(item.key);
                        values1.push(item.value);
                    });

                    const data1 = {
                        labels: labels1,
                        datasets: [{
                                label: "Thống kê số lượng đơn hàng tháng này",
                                data: values1,
                                backgroundColor: bgColor,
                                borderColor: borColor,
                                borderWidth: 1
                            }]
                    };
                    const config1 = {
                        type: 'bar',
                        data: data1,
                        options: {
                            scales: {
                                y: {
                                    beginAtZero: true
                                }
                            },
                        },
                    };

                    const labels2 = [];
                    const values2 = [];
                    res.jsonIncomeData.map(item => {
                        labels2.push(item.key);
                        values2.push(item.value);
                    });

                    const data2 = {
                        labels: labels2,
                        datasets: [{
                                label: "Thống kê số tiền tháng này",
                                data: values2,
                                backgroundColor: bgColor,
                                borderColor: borColor,
                                borderWidth: 1
                            }]
                    };

                    const config2 = {
                        type: 'doughnut',
                        data: data2,
                    };

                    $("#order-count").text(orderCount);
                    $("#increase-rate").text(increaseRate + '%');
                    $("#register-count").text(registerCount);
                    $("#statistic-sum").text(statisticSum);

                    $("#chartProduct1").remove();
                    $("#chartProduct2").remove();

                    $("#chartContainer1").append('<canvas id="chartProduct1"></canvas>');
                    $("#chartContainer2").append('<canvas id="chartProduct2"></canvas>');

                    const myBarChart1 = new Chart(document.getElementById('chartProduct1'), config1);
                    const myBarChart2 = new Chart(document.getElementById('chartProduct2'), config2);
                } else {
                    alert("Có lỗi xảy ra");
                }
            }).catch(error => console.log(error)).finally();
        });

        $("#year").click((e) => {
            let button = $(e.target);
            let parentButton = $("#condition");
            parentButton.text(button.text());

            axios.get('<c:url value="/api/statistic/year" />').then(response => {
                const res = response.data;
                if (res.message === "success") {
                    const orderCount = res.orderCount;
                    const increaseRate = res.increaseRate;
                    const registerCount = res.registerCount;
                    const statisticSum = res.statisticSum;
                    const labels1 = [];
                    const values1 = [];
                    res.jsonCountData.map(item => {
                        labels1.push(item.key);
                        values1.push(item.value);
                    });

                    const data1 = {
                        labels: labels1,
                        datasets: [{
                                label: "Thống kê số lượng đơn hàng tháng này",
                                data: values1,
                                backgroundColor: bgColor,
                                borderColor: borColor,
                                borderWidth: 1
                            }]
                    };
                    const config1 = {
                        type: 'bar',
                        data: data1,
                        options: {
                            scales: {
                                y: {
                                    beginAtZero: true
                                }
                            }
                        },
                    };

                    const labels2 = [];
                    const values2 = [];
                    res.jsonIncomeData.map(item => {
                        labels2.push(item.key);
                        values2.push(item.value);
                    });

                    const data2 = {
                        labels: labels2,
                        datasets: [{
                                label: "Thống kê số tiền tháng này",
                                data: values2,
                                backgroundColor: bgColor,
                                borderColor: borColor,
                                borderWidth: 1
                            }]
                    };

                    const config2 = {
                        type: 'doughnut',
                        data: data2,
                    };

                    $("#order-count").text(orderCount);
                    $("#increase-rate").text(increaseRate + '%');
                    $("#register-count").text(registerCount);
                    $("#statistic-sum").text(statisticSum);

                    $("#chartProduct1").remove();
                    $("#chartProduct2").remove();

                    $("#chartContainer1").append('<canvas id="chartProduct1"></canvas>');
                    $("#chartContainer2").append('<canvas id="chartProduct2"></canvas>');

                    const myBarChart1 = new Chart(document.getElementById('chartProduct1'), config1);
                    const myBarChart2 = new Chart(document.getElementById('chartProduct2'), config2);
                } else {
                    alert("Có lỗi xảy ra");
                }
            }).catch(error => console.log(error)).finally();
        });

        $("#all").click((e) => {
            let button = $(e.target);
            let parentButton = $("#condition");
            parentButton.text(button.text());

            axios.get('<c:url value="/api/statistic/all" />').then(response => {
                const res = response.data;
                if (res.message === "success") {
                    const orderCount = res.orderCount;
                    const increaseRate = res.increaseRate;
                    const registerCount = res.registerCount;
                    const statisticSum = res.statisticSum;
                    const labels1 = [];
                    const values1 = [];
                    res.jsonCountData.map(item => {
                        labels1.push(item.key);
                        values1.push(item.value);
                    });

                    const data1 = {
                        labels: labels1,
                        datasets: [{
                                label: "Thống kê số lượng đơn hàng từ năm 2022 đến nay",
                                data: values1,
                                backgroundColor: bgColor,
                                borderColor: borColor,
                                borderWidth: 1
                            }]
                    };
                    const config1 = {
                        type: 'bar',
                        data: data1,
                        options: {
                            scales: {
                                y: {
                                    beginAtZero: true
                                }
                            }
                        },
                    };

                    const labels2 = [];
                    const values2 = [];
                    res.jsonIncomeData.map(item => {
                        labels2.push(item.key);
                        values2.push(item.value);
                    });

                    const data2 = {
                        labels: labels2,
                        datasets: [{
                                label: "Thống kê số tiền từ năm 2022 đến nay",
                                data: values2,
                                backgroundColor: bgColor,
                                borderColor: borColor,
                                borderWidth: 1
                            }]
                    };

                    const config2 = {
                        type: 'doughnut',
                        data: data2,
                    };

                    $("#order-count").text(orderCount);
                    $("#increase-rate").text(increaseRate);
                    $("#register-count").text(registerCount);
                    $("#statistic-sum").text(statisticSum);

                    $("#chartProduct1").remove();
                    $("#chartProduct2").remove();

                    $("#chartContainer1").append('<canvas id="chartProduct1"></canvas>');
                    $("#chartContainer2").append('<canvas id="chartProduct2"></canvas>');

                    const myBarChart1 = new Chart(document.getElementById('chartProduct1'), config1);
                    const myBarChart2 = new Chart(document.getElementById('chartProduct2'), config2);
                } else {
                    alert("Có lỗi xảy ra");
                }
            }).catch(error => console.log(error)).finally();
        });
    });
</script>
