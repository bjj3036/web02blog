$(function () {
    init()
})

let init = () => {
    loadAllPostDesc()
}

let loadAllPostDesc = () => {
    $.ajax({
            url: 'http://localhost:8080/post/readAll',
            success: function (result) {
                let listBox = $('#post_list');
                data = result.data;
                data.forEach(e => {
                    listBox.append(`<li onclick="viewPost(${e.id})">${e.title}</li>`)
                })
                viewPost(data[0].id)
            }
        }
    )
}

let viewPost = id => {
    $.ajax({
            url: 'http://localhost:8080/post/readById/' + id,
            success: function (result) {
                let postTitle = $('#post_title'),
                    postContent = $('#post_content'),
                    author = $('#post_author'),
                    created = $('#post_created')
                postTitle.html(result.data.title)
                author.html(result.data.username)
                created.html(result.data.created)
                postContent.html(result.data.content)
            },
            error: function (e) {
                console.log('error: ' + e)
            }
        }
    )
}