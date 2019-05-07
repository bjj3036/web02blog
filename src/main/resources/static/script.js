let user = {
    "id": 1,
    "account": "bjj3036",
    "name": "baejunjae",
    "email": "aaa@naver.com",
    "phone": "010-0000-1111",
    "created": "2019-04-29 15:35:32",
    "modified": "2019-04-29 15:35:32"
}

const apiURL = 'http://localhost:8080'

$(function () {
    init()
})

let init = () => {
    loadAllPostDesc()
    closeEditPost()
    viewUser(user)
    getLatestPost()
}

let getLatestPost = () => {
    $.ajax({
        url: apiURL + "/post/readLatest/" + user.id,
        success: function (result) {
            viewPost(result.data)
        }
    })
}

let loadAllPostDesc = () => {
    let listBox = $('#post_list');
    listBox.html('')
    $.ajax({
            url: apiURL + '/post/readAll',
            success: function (result) {
                data = result.data;
                data.forEach(e => {
                    listBox.append(`<li id="li${e.id}" onclick="selectPost(${e.id})">${e.title}</li>`)
                })
            }
        }
    )
}

let selectPost = id => {
    getPost(id, viewPost)
}

let getPost = (id, cb) => {
    $.ajax({
        url: apiURL + '/post/readById/' + id,
        success: function (result) {
            cb(result.data)
        }
    })
}

let viewPost = post => {
    if (!post)
        return;
    let postTitle = $('#post_title'),
        postContent = $('#post_content'),
        author = $('#post_author'),
        created = $('#post_created')
    postTitle.html(post.title)
    author.html(post.username)
    created.html(post.created)
    postContent.html(post.content)

    $('#button1_post').attr('post_id', post.id)
    $('#button2_post').attr('post_id', post.id)
    closeEditPost()
}

let viewUser = function (pUser) {
    $('#user_id').html(pUser.account)
    $('#user_name').html(pUser.name)
    $('#user_created').html(calcJoinedTime(pUser.created))
}

let calcJoinedTime = function (joined) {
    let joinedTime = Date.parse(joined) / 1000
    let now = new Date().getTime() / 1000
    let diff = now - joinedTime
    let day, hour, minute;
    day = Math.floor(diff / (60 * 60 * 24))
    diff %= (60 * 60 * 24)
    hour = Math.floor(diff / (60 * 60))
    diff %= (60 * 60)
    minute = Math.floor(diff / (60))
    return day + "일 " + hour + "시간 " + minute + "분"
}

let showEditPost = function (id) {
    let edit_title = $('#edit_title'),
        edit_content = $('#edit_content'),
        post_title = $('#post_title'),
        post_info_box = $('#post_info_box'),
        post_content = $('#post_content')
    let button1 = $('#button1_post')
    let button2 = $('#button2_post')
    edit_title.show()
    edit_content.show()
    post_title.hide()
    post_info_box.hide()
    post_content.hide()
    button1.html('확인')
    button2.html('취소')
    if (id) {
        getPost(id, post => {
            edit_title.val(post.title)
            edit_content.val(post.content)
        })
    } else {
        $('#button1_post').removeAttr('post_id')
        $('#button2_post').removeAttr('post_id')
        edit_title.val('')
        edit_content.val('')
    }
}

let closeEditPost = function () {
    let edit_title = $('#edit_title'),
        edit_content = $('#edit_content'),
        post_title = $('#post_title'),
        post_info_box = $('#post_info_box'),
        post_content = $('#post_content')
    edit_title.hide()
    edit_content.hide()
    post_title.show()
    post_info_box.show()
    post_content.show()
}

let button1Click = function (e) {
    let button1 = $(e.target)
    let button2 = $('#button2_post')

    if (button1.html() == '수정') {
        showEditPost(button1.attr('post_id'))

    } else {
        let edit_title = $('#edit_title')
        let edit_content = $('#edit_content')
        button1.html('수정')
        button2.html('삭제')
        if (button1.attr('post_id')) {
            $.ajax({
                url: apiURL + '/post/update',
                type: 'PUT',
                contentType: "application/json",
                dataType: 'JSON',
                data: JSON.stringify({
                    id: button1.attr('post_id'),
                    title: edit_title.val(),
                    content: edit_content.val()
                }),
                success: function (result) {
                    $('#li' + result.data.id).html(result.data.title)
                    getPost(result.data.id, viewPost)
                }
            })
        } else {
            $.ajax({
                url: apiURL + '/post/create',
                type: 'POST',
                contentType: "application/json",
                dataType: 'JSON',
                data: JSON.stringify({
                    userId: user.id,
                    title: edit_title.val(),
                    content: edit_content.val()
                }),
                success: function (result) {
                    console.log(result)
                    loadAllPostDesc()
                    getPost(result.data.id, viewPost)
                }
            })
        }
    }
}

let button2Click = function (e) {
    let button1 = $('#button1_post')
    let button2 = $(e.target)

    if (button2.html() == '삭제') {
        $.ajax({
            url: apiURL + '/post/remove/' + (button1.attr('post_id') || '-1'),
            type: 'DELETE',
            success: function (result) {
                loadAllPostDesc()
                getLatestPost()
            }
        })
    } else {
        button1.html('수정')
        button2.html('삭제')
        closeEditPost()
        if (button1.attr('post_id')) {
            getPost(button1.attr('post_id'), viewPost)
        } else {
            getLatestPost()
        }
    }
}