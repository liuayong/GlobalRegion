git pull origin && git pull gitee
git push origin && git push gitee
[alias]
	unstage = reset HEAD
	push = push origin & git push gitee

```
$  git pull gitee
You asked to pull from the remote 'gitee', but did not specify
a branch. Because this is not the default configured remote
for your current branch, you must specify a branch on the command line.

 git pull gitee master
From github.com:liuayong/GlobalRegion
 * branch            master     -> FETCH_HEAD
Already up to date.

[branch "master"]
	remote = gitee
	merge = refs/heads/master	


[core]
	repositoryformatversion = 0
	filemode = false
	bare = false
	logallrefupdates = true
	symlinks = false
	ignorecase = true
[remote "gitee"]
	url = git@github.com:liuayong/GlobalRegion.git
	fetch = +refs/heads/*:refs/remotes/origin/*
[branch "master"]
	remote = origin
	merge = refs/heads/master
	
[branch "master"]
	remote = gitee
	merge = refs/heads/master	
	
[remote "origin"]
	url = https://gitee.com/springs-study/global-region.git
	fetch = +refs/heads/*:refs/remotes/gitee/*
[alias]
	unstage = reset HEAD
	push = push origin & git push gitee
	pull = pull origin & git pull gitee
```
